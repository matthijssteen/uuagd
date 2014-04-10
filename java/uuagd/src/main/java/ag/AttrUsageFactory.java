package ag;

import static ag.AttrKind.CHN;
import static ag.AttrKind.INH;
import static ag.AttrKind.SYN;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AttrUsageFactory {
	final public Map<String, Map<String, List<AttrUsage>>> attrUsages;

	final private PrettyListener agInfo;
	final private Settings settings;

	public AttrUsageFactory(PrettyListener agInfo, Settings settings) {
		this.agInfo = agInfo;
		this.settings = settings;
		this.attrUsages = new HashMap<String, Map<String, List<AttrUsage>>>();
	}

	public AttrUsage create(Set<String> dataTypes, Set<String> alts, String scope, String name) {
		Map<String, Map<String, Set<AttrDef>>> defsByDataType =
				new HashMap<String, Map<String, Set<AttrDef>>>();
		AttrUsage usage = new AttrUsage(scope, name, defsByDataType);

		for (String dataType: dataTypes.contains("*") ? agInfo.kidsByDataType.keySet() : dataTypes) {
			if (!attrUsages.containsKey(dataType)) {
				attrUsages.put(dataType, new HashMap<String, List<AttrUsage>>());
			}

			for (String alt: alts.contains("*") ? agInfo.kidsByDataType.get(dataType).keySet() : alts) {
				if (!attrUsages.get(dataType).containsKey(alt)) {
					attrUsages.get(dataType).put(alt, new ArrayList<AttrUsage>());
				}

				if (!settings.isAttrVisible(new AttrId(dataType, alt, scope, name))) {
					continue;
				}

				attrUsages.get(dataType).get(alt).add(usage);

				Set<AttrDef> defs = new HashSet<AttrDef>();

				// If the scope is neither `lhs` nor `loc` than it has to be an inherited or chained attribute.
				// This means that the attribute is defined for one of its kids, not for the assignee.
				if (!scope.equals("lhs") && !scope.equals("loc")) {
					addDefs(defs, new AttrKind[] {INH, CHN}, agInfo.kidsByDataType.get(dataType).get(alt).get(scope), name);
				} else {
					// Otherwise the attribute could be either synthesized or chained.
					// This is also the case for local scopes, due to the copy-rule.
					addDefs(defs, new AttrKind[] {SYN, CHN}, dataType, name);

					// A local scope could also mean that the attribute is defined on any of its kids,
					// due to the copy-rule.
					if (scope.equals("loc")) {
						Collection<String> kidTypes = agInfo.kidsByDataType.get(dataType).get(alt).values();
						for (String kidType: kidTypes) {
							addDefs(defs, new AttrKind[] {INH, CHN}, kidType, name);
						}
					}
				}

				if (!defs.isEmpty()) {
					Map<String, Set<AttrDef>> defsByAlt = new HashMap<String, Set<AttrDef>>();
					defsByDataType.put(dataType, defsByAlt);
					defsByAlt.put(alt, defs);
				}
			}
		}

		return usage;
	}

	public AttrUsage create(Set<String> dataTypes, Set<String> alts, String tokenText) {
		String[] parts = tokenText.replaceAll("[^a-zA-Z0-9_\\.']", "").split("\\.");
		return create(dataTypes, alts, parts[0], parts[1]);
	}

	private void addDefs(Set<AttrDef> defs, AttrKind[] kinds, String dataType, String name) {
		if (!agInfo.defsByDataType.containsKey(dataType)) {
			return;
		}
		Map<AttrKind, Map<String, AttrDef>> defsByKind = agInfo.defsByDataType.get(dataType);
		for (AttrKind kind: kinds) {
			if (defsByKind.containsKey(kind)) {
				Map<String, AttrDef> defsByName = defsByKind.get(kind);
				if (defsByName.containsKey(name)) {
					defs.add(defsByName.get(name));
				}
			}
		}
	}
}

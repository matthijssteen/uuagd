package ag.embed;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.pretty.PrettyAGParser;
import parser.pretty.PrettyAGParserBaseListener;
import util.Strings;
import ag.AttrCode;
import ag.AttrId;
import ag.AttrUsage;
import ag.GrammarInfo;
import ag.Settings;

public class Embed extends PrettyAGParserBaseListener {
	final private PrintWriter out;
	final private GrammarInfo agInfo;
	final private Set<String> dataTypes;
	final private Settings settings;
	
	private String dataType;
	private String alt;
	private boolean firstAttr;
	private List<String[]> children;
	
	public Embed(PrintWriter out, GrammarInfo agInfo, Settings settings) throws IOException {
		this.out = out;
		this.agInfo = agInfo;
		this.dataTypes = agInfo.kidsByDataType.keySet();
		this.settings = settings;
	}
	
	@Override
	public void enterRoot(PrettyAGParser.RootContext ctx) {
		out.println("imports {");
		out.println("import qualified UUAGD.Embed as UUAGD");
		if (settings.embedImport != null) {
			out.print("import ");
			out.println(settings.embedImport);
		}
		out.println("}");
		out.println("attr *");
		out.println("  syn uuagd :: {" + settings.castTy + "}");
	}

	@Override
	public void enterData(PrettyAGParser.DataContext ctx) {
		dataType = ctx.AG_DATA_TYPE().getText();
		
		out.println();
		out.println("sem " + dataType);
	}
	
	@Override
	public void enterAlt(PrettyAGParser.AltContext ctx) {
		alt = ctx.AG_TYPE().getText();
		
		out.print("  | ");
		out.print(alt);
		out.print(" lhs.uuagd = UUAGD.embedAlt ");
		out.print(Strings.toLiteral(dataType));
		out.print(" ");
		out.print(Strings.toLiteral(alt));
		out.print(" [");
		
		firstAttr = true;
		
		children = new ArrayList<String[]>();
	}
	
	@Override
	public void enterChild(PrettyAGParser.ChildContext ctx) {
		String name = ctx.IDENT().getText();
		String type = ctx.HS_TYPE().getText();
		
		if (!settings.filterKids || settings.isAttrVisible(new AttrId(dataType, alt, "kid", name))) {
			children.add(new String[]{name, type});
		}
	}
	
	@Override
	public void exitAlt(PrettyAGParser.AltContext ctx) {
		if (agInfo.attrUsages.get(dataType) != null && agInfo.attrUsages.get(dataType).get(alt) != null) {
			if (settings.showCode) {
				Map<String, Map<String, AttrUsage>> attrUsages = new HashMap<String, Map<String, AttrUsage>>();
				for (AttrUsage attrUsage: agInfo.attrUsages.get(dataType).get(alt)) {
					Map<String, AttrUsage> nameMap = attrUsages.get(attrUsage.scope);
					if (nameMap == null) {
						nameMap = new HashMap<String, AttrUsage>();
						attrUsages.put(attrUsage.scope, nameMap);
					}
					nameMap.put(attrUsage.name, attrUsage);
				}
				
				for (AttrCode attrCode: agInfo.attrCodes.get(dataType).get(alt)) {
					List<AttrId> attrIds = new ArrayList<AttrId>(attrCode.attrIds);
					for (AttrId attrId: attrCode.attrIds) {
						if (!attrUsages.containsKey(attrId.scope) || !attrUsages.get(attrId.scope).containsKey(attrId.name)) {
							attrIds.remove(attrId);
						}
					}
					if (attrIds.isEmpty()) {
						continue;
					}
					
					boolean isTuple = attrCode.attrIds.size() != 1;
					
					if (!firstAttr) {
						out.print(", ");
					}
					out.print("UUAGD.embedAttrUsage");
					out.print(isTuple ? "TupleCode" : "Code");
					out.print(" [");
					{
						boolean firstAttr = true;
						for (AttrId attrId: attrCode.attrUsed) {
							if (!settings.isAttrVisible(attrId)) {
								continue;
							}
							if (!firstAttr) {
								out.print(", ");
							}
							out.print("(");
							out.print(Strings.toLiteral(attrId.getScopedName()));
							out.print(", ");
							out.print(settings.castFn);
							out.print(" ");
							out.print(attrId.getUsageName());
							out.print(")");
							firstAttr = false;
						}
					}
					out.print("] ");
					out.print(Strings.toLiteral(attrCode.code));
					out.print(" ");
					if (isTuple) {
						out.print("[");
						boolean firstAttr = true;
						for (AttrId attrId: attrCode.attrIds) {
							if (!firstAttr) {
								out.print(", ");
							}
							out.print("(");
							out.print(Strings.toLiteral(attrId.scope));
							out.print(", ");
							out.print(Strings.toLiteral(attrId.name));
							out.print(", ");
							if (attrIds.contains(attrId)) {
								AttrUsage attrUsage = attrUsages.get(attrId.scope).get(attrId.name);
								
								out.print("Just ");
								printAttrUsageValue(out, attrUsage);
							} else {
								out.print("Nothing");
							}
							out.print(")");
							
							firstAttr = false;
						}
						out.print("]");
					} else {
						AttrId attrId = attrCode.attrIds.get(0);
						AttrUsage attrUsage = attrUsages.get(attrId.scope).get(attrId.name);
						
						out.print(Strings.toLiteral(attrId.scope));
						out.print(" ");
						out.print(Strings.toLiteral(attrId.name));
						out.print(" ");
						printAttrUsageValue(out, attrUsage);
					}
					
					firstAttr = false;
				}
			} else {
				for (AttrUsage attrUsage: agInfo.attrUsages.get(dataType).get(alt)) {
					if (!firstAttr) {
						out.print(", ");
					}
					out.print("UUAGD.embedAttrUsage ");
					out.print(Strings.toLiteral(attrUsage.scope));
					out.print(" ");
					out.print(Strings.toLiteral(attrUsage.name));
					out.print(" ");
					printAttrUsageValue(out, attrUsage);
					
					firstAttr = false;
				}
			}
		}
		for (String[] child: children) {
			String name = child[0];
			String type = child[1];
			if (!firstAttr) {
				out.print(", ");
			}
			if (dataTypes.contains(type)) {
				out.print("UUAGD.embedNonTerminal ");
				out.print(Strings.toLiteral(name));
				out.print(" @");
				out.print(name);
				out.print(".uuagd");
			} else {
				out.print("UUAGD.embedTerminal ");
				out.print(Strings.toLiteral(name));
				out.print(" (");
				out.print(settings.castFn);
				out.print(" @");
				out.print(name);
				out.print(")");
			}
			
			firstAttr = false;
		}
		out.println("]");
	}
	
	private void printAttrUsageValue(PrintWriter out, AttrUsage attrUsage) {
		String type = attrUsage.getType();
		
		out.print("(");
		out.print(settings.castFn);
		out.print(" (@");
		out.print(attrUsage.getAliasScopedName());
		if (type != null) {
			out.print(" :: ");
			out.print(type);
		}
		out.print("))");
	}
}

package ag;

import java.util.HashMap;
import java.util.Map;

import parser.pretty.PrettyAGParser;
import parser.pretty.PrettyAGParserBaseListener;

public class PrettyInfoGatherer extends PrettyAGParserBaseListener {
	final public static Map<Integer, AttrKind> ATTR_KIND_MAPPING =
			new HashMap<Integer, AttrKind>();

	static {
		ATTR_KIND_MAPPING.put(PrettyAGParser.SYN, AttrKind.SYN);
		ATTR_KIND_MAPPING.put(PrettyAGParser.INH, AttrKind.INH);
		ATTR_KIND_MAPPING.put(PrettyAGParser.CHN, AttrKind.CHN);
	}

	final private GrammarInfo agInfo;
	
	private Map<AttrKind, Map<String, AttrDef>> defsByKind;
	private Map<String, AttrDef> defsByName;

	private Map<String, Map<String, String>> kidsByAlt;
	private Map<String, String> kidsByName;
	
	private String dataType;
	private AttrKind kind;
	private String alt;

	public PrettyInfoGatherer(GrammarInfo agInfo) {
		this.agInfo = agInfo;
	}
	
	@Override
	public void enterData(PrettyAGParser.DataContext ctx) {
		dataType = ctx.AG_DATA_TYPE().getText();

		defsByKind = new HashMap<AttrKind, Map<String, AttrDef>>();
		agInfo.defsByDataType.put(dataType, defsByKind);

		kidsByAlt = new HashMap<String, Map<String, String>>();
		agInfo.kidsByDataType.put(dataType, kidsByAlt);
	}

	@Override
	public void enterAttrKind(PrettyAGParser.AttrKindContext ctx) {
		kind = ATTR_KIND_MAPPING.get(ctx.start.getType());

		defsByName = new HashMap<String, AttrDef>();
		defsByKind.put(kind, defsByName);
	}

	@Override
	public void enterAlt(PrettyAGParser.AltContext ctx) {
		alt = ctx.AG_TYPE().getText();

		kidsByName = new HashMap<String, String>();
		kidsByAlt.put(alt, kidsByName);
	}

	@Override
	public void enterChild(PrettyAGParser.ChildContext ctx) {
		String name = ctx.IDENT().getText();
		String type = ctx.HS_TYPE().getText();

		kidsByName.put(name, type);
	}

	@Override
	public void enterAttr(PrettyAGParser.AttrContext ctx) {
		String name = ctx.IDENT().getText();
		String type = ctx.HS_TYPE().getText();

		defsByName.put(name, new AttrDef(dataType, kind, name, type));
	}
}

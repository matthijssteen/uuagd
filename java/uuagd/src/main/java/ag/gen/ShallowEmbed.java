package ag.gen;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ag.AttrId;
import ag.AttrUsage;
import ag.PrettyListener;
import ag.Settings;
import parser.pretty.PrettyAGParser;
import parser.pretty.PrettyAGParserBaseListener;
import util.Strings;

public class ShallowEmbed extends PrettyAGParserBaseListener {
	final private PrintWriter out;
	final private Set<String> dataTypes;
	final private Map<String, Map<String, List<AttrUsage>>> attrUsages;
	final private Settings settings;
	
	private String dataType;
	private String alt;
	private boolean firstAttr;
	
	public ShallowEmbed(PrintWriter out, PrettyListener agInfo, Map<String, Map<String, List<AttrUsage>>> attrUsages, Settings settings) throws IOException {
		this.out = out;
		this.dataTypes = agInfo.kidsByDataType.keySet();
		this.attrUsages = attrUsages;
		this.settings = settings;
	}
	
	@Override
	public void enterRoot(PrettyAGParser.RootContext ctx) {
		out.println("imports {");
		out.println("import qualified UUAGD.CodeGen as UUAGD");
		if (!settings.noDefaultImport) {
			out.println("import UUAGD.Html.CodeGen");
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
		out.print(" lhs.uuagd = UUAGD.markupAlt ");
		out.print(Strings.toLiteral(dataType));
		out.print(" ");
		out.print(Strings.toLiteral(alt));
		out.print(" [");
		
		firstAttr = true;
	}
	
	@Override
	public void enterChild(PrettyAGParser.ChildContext ctx) {
		String name = ctx.IDENT().getText();
		String type = ctx.HS_TYPE().getText();
		
		if (!settings.filterKids || settings.isAttrVisible(new AttrId(dataType, alt, "kid", name))) {
			if (!firstAttr) {
				out.print(", ");
			}
			out.print("UUAGD.markup");
			out.print(dataTypes.contains(type) ? "Terminal" : "NonTerminal");
			out.print(" ");
			out.print(Strings.toLiteral(name));
			out.print(" ");
			if (dataTypes.contains(type)) {
				out.print("@");
				out.print(name);
				out.print(".uuagd");
			} else {
				out.print("(");
				out.print(settings.castFn);
				out.print(" @");
				out.print(name);
				out.print(")");
			}
			
			firstAttr = false;
		}
	}
	
	@Override
	public void exitAlt(PrettyAGParser.AltContext ctx) {
		if (attrUsages.get(dataType) != null && attrUsages.get(dataType).get(alt) != null) {
			for (AttrUsage attr: attrUsages.get(dataType).get(alt)) {
				String type = attr.getType();
				
				if (!firstAttr) {
					out.print(", ");
				}
				out.print("UUAGD.markupAttrUsage ");
				out.print(Strings.toLiteral(attr.scope));
				out.print(" ");
				out.print(Strings.toLiteral(attr.name));
				out.print(" (");
				out.print(settings.castFn);
				out.print(" (@");
				out.print(attr.getAliasScopedName());
				if (type != null) {
					out.print(" :: ");
					out.print(type);
				}
				out.print("))");
				
				firstAttr = false;
			}
		}
		
		out.println("]");
	}
}

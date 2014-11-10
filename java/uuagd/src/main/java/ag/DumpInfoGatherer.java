package ag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import parser.dump.DumpAGLexer;
import parser.dump.DumpAGParser.CodeContext;
import parser.dump.DumpAGParser.DataConContext;
import parser.dump.DumpAGParser.DataConValContext;
import parser.dump.DumpAGParser.DataConValsContext;
import parser.dump.DumpAGParser.MapEntryContext;
import parser.dump.DumpAGParserBaseListener;

public class DumpInfoGatherer extends DumpAGParserBaseListener {
	final private GrammarInfo agInfo; 
	
	private String dataCon;
	private List<String> dataConVals;
	private String dataType;
	private String alt;
	
	private List<AttrId> attrIds;
	private List<AttrId> attrUsed;
		
	public DumpInfoGatherer(GrammarInfo agInfo) {
		this.agInfo = agInfo;
	}
	
	@Override
	public void enterDataCon(DataConContext ctx) {
		dataCon = ctx.DATA_CON().getText();
		dataConVals = new ArrayList<String>();
	}

	@Override
	public void exitDataConVals(DataConValsContext ctx) {
		if (dataCon.equals("Rule_Rule")) {
			attrIds = new ArrayList<AttrId>();
			attrUsed = new ArrayList<AttrId>();
		} else if (dataCon.equals("Nonterminal_Nonterminal")) {
			dataType = dataConVals.get(0);
		} else if (dataCon.equals("Production_Production")) {
			alt = dataConVals.get(0);
		} else if (dataCon.equals("Pattern_Alias")) {
			String scope = dataConVals.get(0);
			String name = dataConVals.get(1);
			attrIds.add(new AttrId(dataType, alt, scope, name));
		}
	}

	@Override
	public void enterDataConVal(DataConValContext ctx) {
		dataConVals.add(ctx.getChild(0).getText());
	}

	@Override
	public void enterCode(CodeContext ctx) {
		for (ParseTree child: ctx.children) {
			Token token = (Token)child.getPayload();
			if (token.getType() == DumpAGLexer.ATTR_USAGE) {
				attrUsed.add(new AttrId(token.getText().substring(1)));
			}
		}
	}
	
	@Override
	public void exitMapEntry(MapEntryContext ctx) {
		if (ctx.KEY().getText().equals("txt")) {
			Map<String, List<AttrCode>> attrCodesByAlt = agInfo.attrCodes.get(dataType);
			if (attrCodesByAlt == null) {
				attrCodesByAlt = new HashMap<String, List<AttrCode>>();
				agInfo.attrCodes.put(dataType, attrCodesByAlt);
			}
			List<AttrCode> attrCodes = attrCodesByAlt.get(alt);
			if (attrCodes == null) {
				attrCodes = new ArrayList<AttrCode>();
				attrCodesByAlt.put(alt, attrCodes);
			}
			attrCodes.add(new AttrCode(attrIds, attrUsed, ctx.val().getText()));
		}
	}
}
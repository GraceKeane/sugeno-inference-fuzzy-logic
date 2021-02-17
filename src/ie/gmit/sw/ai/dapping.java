package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
 * @author Grace Keane
 *  
 */

public class dapping {
	/*
	 * 	COGS: 52.77% (Sugeno consequent)
	 * 	COGS: 75% (Sugeno a)
	 *  COG: 53.41%
	 *  MOM: 37.55%
	 *  SOM: 73.4%
	 *  LOM: 99.9% 
	 */
	
	private static final String FCL_FILE = "./FCL/dappingSugeno.txt";
	
	public double getDappingLevel(int windBeaufort, int tempCelsius) {
		FIS fis = FIS.load(FCL_FILE, true);
		FunctionBlock fb = fis.getFunctionBlock("getDappingLevel");
		fis.setVariable("wind", windBeaufort);
		fis.setVariable("temperature", tempCelsius);
		fis.evaluate();
		Variable dapping = fb.getVariable("dapping");
		JFuzzyChart.get().chart(fis);
		JFuzzyChart.get().chart(dapping.getDefuzzifier(), "Dapping Level", true);

		return dapping.getValue();
		}
	
	public static void main(String [] args) {
		dapping d = new dapping();
		double Dapping = d.getDappingLevel(8, 10);
		System.out.println(Dapping);
	}
}

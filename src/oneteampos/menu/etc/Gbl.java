package oneteampos.menu.etc;

import java.awt.GridBagConstraints;

public class Gbl {
	
	public static GridBagConstraints getGbc() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}
	
	public static GridBagConstraints setting(GridBagConstraints gbc, double weightx, double weighty, int gridx, int gridy) {
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		return gbc;
	}
	
	public static GridBagConstraints setting(GridBagConstraints gbc, double weightx, double weighty, int gridx, int gridy, int gridWidth, int gridHeight) {
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;
		return gbc;
	}
}

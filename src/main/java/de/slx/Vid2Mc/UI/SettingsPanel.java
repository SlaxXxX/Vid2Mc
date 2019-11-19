package de.slx.Vid2Mc.UI;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Function;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jcodec.common.model.Size;

public class SettingsPanel extends JPanel {
	private static final long serialVersionUID = 8641958972310187272L;

	private float ratio;
	private int originalYRes;
	private SettingType[] changeQueue = { SettingType.pixel, SettingType.size, SettingType.block };

	private NumTextField pixelX = new NumTextField(0, (w) -> w / getRatio(), SettingType.pixel);
	private NumTextField pixelY = new NumTextField(0, (h) -> h * getRatio(), SettingType.pixel);
	private NumTextField blockX = new NumTextField(0f, (w) -> w / getRatio(), SettingType.block);
	private NumTextField blockY = new NumTextField(0f, (h) -> h * getRatio(), SettingType.block);
	private NumTextField pixelSize = new NumTextField(0.1f, null, SettingType.size);
	private NumTextField fps = new NumTextField(20, null, SettingType.other);

	private FocusListener focusListener = new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
			checkValue((NumTextField) e.getSource());
		}

		@Override
		public void focusGained(FocusEvent e) {
		}
	};

	public SettingsPanel() {
		pixelX.setPartner(pixelY);
		pixelY.setPartner(pixelX);
		blockX.setPartner(blockY);
		blockY.setPartner(blockX);

		pixelX.addActionListener(this::enteredValue);
		pixelY.addActionListener(this::enteredValue);
		blockX.addActionListener(this::enteredValue);
		blockY.addActionListener(this::enteredValue);
		pixelSize.addActionListener(this::enteredValue);
		fps.addActionListener(this::enteredValue);

		pixelX.addFocusListener(focusListener);
		pixelY.addFocusListener(focusListener);
		blockX.addFocusListener(focusListener);
		blockY.addFocusListener(focusListener);
		pixelSize.addFocusListener(focusListener);
		fps.addFocusListener(focusListener);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2, 2, 2, 2);

		c.gridwidth = 4;
		JLabel title = new JLabel("Settings");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		add(title, c);

		{
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0;
			add(new JLabel("Pixel: "), c);

			c.weightx = 0.2;
			c.gridx = GridBagConstraints.RELATIVE;
			add(pixelX, c);

			c.weightx = 0;
			add(new JLabel("x"), c);

			c.weightx = 0.2;
			add(pixelY, c);
		}
		{
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 0;
			add(new JLabel("Blocks: "), c);

			c.weightx = 0.2;
			c.gridx = GridBagConstraints.RELATIVE;
			add(blockX, c);

			c.weightx = 0;
			add(new JLabel("x"), c);

			c.weightx = 0.2;
			add(blockY, c);
		}
		{
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 0;
			add(new JLabel("Pixelsize: "), c);

			c.gridx = GridBagConstraints.RELATIVE;
			c.weightx = 0;
			add(pixelSize, c);
		}
		{
			c.gridx = 0;
			c.gridy = 4;
			c.weightx = 0;
			add(new JLabel("FPS: "), c);

			c.gridx = GridBagConstraints.RELATIVE;
			c.weightx = 0;
			add(fps, c);
		}
	}

	public void setEnabled(boolean enabled) {
		for (Component cp : this.getComponents()) {
			cp.setEnabled(enabled);
		}
	}

	public float getRatio() {
		return ratio;
	}

	private void enteredValue(ActionEvent e) {
		checkValue((NumTextField) e.getSource());
	}

	private void checkValue(NumTextField field) {
		Number newNum;
		try {
			if (field.value instanceof Integer)
				newNum = Integer.parseInt(field.getText().replace(',', '.'));
			else
				newNum = Float.parseFloat(field.getText().replace(',', '.'));
		} catch (Exception e2) {
			field.setText(NumTextField.getValString(field.value));
			return;
		}
		field.value = newNum;
		field.setText(NumTextField.getValString(field.value));
		field.editPartner();
		if (field.settingType != SettingType.other)
			updateValues(field.settingType);
	}

	private void updateValues(SettingType eventType) {
		for (int i = changeQueue.length - 1; i > 0; i--) {
			if (changeQueue[i] == eventType) {
				changeQueue[i] = changeQueue[i - 1];
				changeQueue[i - 1] = eventType;
			}
		}

		switch (changeQueue[2]) {
		case pixel:
			pixelX.setVal(blockX.value.floatValue() / pixelSize.value.floatValue());
			pixelY.setVal(blockY.value.floatValue() / pixelSize.value.floatValue());
			break;
		case block:
			blockX.setVal(pixelX.value.floatValue() * pixelSize.value.floatValue());
			blockY.setVal(pixelY.value.floatValue() * pixelSize.value.floatValue());
			break;
		case size:
			pixelSize.setVal(blockX.value.floatValue() / pixelX.value.floatValue());
			break;
		}
	}

	private void update() {
		pixelX.setText("" + pixelX.value.intValue());
		pixelY.setText("" + pixelY.value.intValue());
		blockX.setText("" + blockX.value.floatValue());
		blockY.setText("" + blockY.value.floatValue());
		pixelSize.setText("" + pixelSize.value.floatValue());
		fps.setText("" + fps.value.intValue());
	}

	public void setVideoInformation(Size size) {
		int width = size.getWidth();
		int height = size.getHeight();
		originalYRes = height;
		this.ratio = (float) width / height;
		pixelX.setValue(width);
		pixelY.setValue(height);
		blockX.setValue(width * pixelSize.value.floatValue());
		blockY.setValue(height * pixelSize.value.floatValue());
		update();
	}

	public de.slx.Vid2Mc.Task.Settings generateSettings() {
		double yScale = (double) pixelY.value.doubleValue() / originalYRes;
		int delay = (int) (20 / Math.min(20, fps.value.doubleValue()));
		return new de.slx.Vid2Mc.Task.Settings(yScale, pixelSize.value.doubleValue(), delay);
	}

}

class NumTextField extends JTextField {
	Number value;
	NumTextField partner;
	Function<Float, Float> editFunction;
	SettingType settingType;

	NumTextField(Number val, Function<Float, Float> function, SettingType type) {
		super(getValString(val));
		this.value = val;
		this.editFunction = function;
		this.settingType = type;
	}

	void setValue(Number val) {
		value = val;
	}

	static String getValString(Number val) {
		if (val instanceof Integer)
			return "" + val.intValue();
		else
			return "" + val.floatValue();
	}

	void setPartner(NumTextField p) {
		partner = p;
	}

	void setVal(float val) {
		if (value instanceof Integer)
			value = (int) val;
		else
			value = val;
		this.setText(getValString(value));
	}

	void editPartner() {
		if (editFunction != null && partner != null)
			partner.setVal(editFunction.apply(value.floatValue()));
	}
}

enum SettingType {
	pixel, block, size, other;
}
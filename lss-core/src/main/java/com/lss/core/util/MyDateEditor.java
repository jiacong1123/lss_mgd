package com.lss.core.util;

import java.text.DateFormat;
import java.text.ParseException;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;

public class MyDateEditor extends CustomDateEditor {


	private final DateFormat dateFormat;

	private final boolean allowEmpty;

	private final int exactDateLength;
	
	public MyDateEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}
		else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
			throw new IllegalArgumentException(
					"Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
		}
		else {
			try {
				if(text == null){
					setValue(text);
				}else{
					setValue(this.dateFormat.parse(DateUtils.timestampToTimeStr(Long.parseLong(text))));
				}
			}
			catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}
}

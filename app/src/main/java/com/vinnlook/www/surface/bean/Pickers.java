package com.vinnlook.www.surface.bean;

/**
 *
 * @author zengtao 2015年5月20日下午7:18:14
 *
 */
public class Pickers extends BindingBankCardBean  {

	private static final long serialVersionUID = 1L;

	private String showConetnt;
	private String showId;

	public String getShowConetnt() {
		return showConetnt;
	}

	public String getShowId() {
		return showId;
	}

	public Pickers(String showConetnt, String showId) {
		super();
		this.showConetnt = showConetnt;
		this.showId = showId;
	}

	public Pickers() {
		super();
	}

}

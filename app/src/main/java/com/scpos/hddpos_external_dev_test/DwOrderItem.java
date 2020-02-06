package com.scpos.hddpos_external_dev_test;

import java.io.Serializable;

public class DwOrderItem implements Serializable, java.lang.Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private double num_price_save;
	private String ch_bookno;
	private String vch_spec;
	private String int_material_rate;
	private String ch_fixprice;
	private double int_rate;

	// *******************************************************************************************************************************
	private String ch_eatflag;
	private String int_flowID;
	private String ch_billno;
	private String ch_tableno;
	private int int_id;
	private String ch_dishno;
	private String vch_barcode;
	private String ch_weight;
	private String vch_dishname;
	private String ch_unitno;
	private String ch_unitname;
	private double num_price;
	private double num_price_org;
	private double num_price_add;
	private double num_num;
	private double num_each_each_num;
	private double num_back;
	private String vch_back_operID;
	private String dt_back_operdate;
	private double int_discount;
	private String vch_dis_operID;
	private String dt_dis_operdate;
	private String vch_memberno;
	private String ch_consult;
	private String vch_print_memo;
	private String ch_suitflag;
	private String ch_suitno;
	private String ch_specialflag;
	private String ch_presentflag;
	private String ch_discountflag;
	private String vch_pre_operID;
	private String dt_pre_operdate;
	private String vch_operID;
	private String dt_operdate;
	private String ch_togono;
	private String ch_payno;
	private String ls_unitname;
	private double num_each_num;
	private double num_each_price;
	private double num_each_each_price;
	private double num_warm;
	private double blotout;
	private String ch_curprice;
	private String ch_discount;
	private String re_pay;
	private String groupname;
	private String biggroupname;
	private String smallgroupname;
	private String discountflag;
	private String dflag;
	private double specialmoney;
	private int suit_change_id;
	private String isOrdered;
	private String ls_labelprint_flag;
	private String flag;
	private String vch_printer;
	private String vch_outprint;
	private String outpflag;
	private String ch_printflag;
	private String printflag;
	private String ch_outprintfalg;

	// *******************************************************************************************************************************
	private String printer;
	private double num_back_warn = 0;
	private double num_temp_num = 0;
	private String isprintflag = "";
	private String isoutflag = "";
	private String vch_spell = "";

	public String getVch_barcode() {
		return vch_barcode;
	}

	public void setVch_barcode(String vch_barcode) {
		this.vch_barcode = vch_barcode;
	}

	public String getCh_weight() {
		return ch_weight;
	}

	public void setCh_weight(String ch_weight) {
		this.ch_weight = ch_weight;
	}

	public double getNum_price_save() {
		return num_price_save;
	}

	public void setNum_price_save(double num_price_save) {
		this.num_price_save = num_price_save;
	}

	public double getInt_rate() {
		return int_rate;
	}

	public void setInt_rate(double int_rate) {
		this.int_rate = int_rate;
	}

	public String getCh_bookno() {
		return ch_bookno;
	}

	public void setCh_bookno(String ch_bookno) {
		this.ch_bookno = ch_bookno;
	}

	public String getVch_spec() {
		return vch_spec;
	}

	public void setVch_spec(String vch_spec) {
		this.vch_spec = vch_spec;
	}

	public String getInt_material_rate() {
		return int_material_rate;
	}

	public void setInt_material_rate(String int_material_rate) {
		this.int_material_rate = int_material_rate;
	}

	public String getCh_fixprice() {
		return ch_fixprice;
	}

	public void setCh_fixprice(String ch_fixprice) {
		this.ch_fixprice = ch_fixprice;
	}

	public String getCh_eatflag() {
		return ch_eatflag;
	}

	public void setCh_eatflag(String ch_eatflag) {
		this.ch_eatflag = ch_eatflag;
	}

	public String getCh_outprintfalg() {
		return ch_outprintfalg;
	}

	public void setCh_outprintfalg(String ch_outprintfalg) {
		this.ch_outprintfalg = ch_outprintfalg;
	}

	public String getPrintflag() {
		return printflag;
	}

	public void setPrintflag(String printflag) {
		this.printflag = printflag;
	}

	public String getVch_spell() {
		return vch_spell;
	}

	public void setVch_spell(String vch_spell) {
		this.vch_spell = vch_spell;
	}

	public String getIsprintflag() {
		return isprintflag;
	}

	public void setIsprintflag(String isprintflag) {
		this.isprintflag = isprintflag;
	}

	public String getIsoutflag() {
		return isoutflag;
	}

	public void setIsoutflag(String isoutflag) {
		this.isoutflag = isoutflag;
	}

	public double getNum_temp_num() {
		return num_temp_num;
	}

	public void setNum_temp_num(double num_temp_num) {
		this.num_temp_num = num_temp_num;
	}

	public double getNum_back_warn() {
		return num_back_warn;
	}

	public void setNum_back_warn(double num_back_warn) {
		this.num_back_warn = num_back_warn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPrinter() {
		return printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public String getVch_printer() {
		return vch_printer;
	}

	public void setVch_printer(String vch_printer) {
		this.vch_printer = vch_printer;
	}

	public String getVch_outprint() {
		return vch_outprint;
	}

	public void setVch_outprint(String vch_outprint) {
		this.vch_outprint = vch_outprint;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLs_labelprint_flag() {
		return ls_labelprint_flag;
	}

	public void setLs_labelprint_flag(String ls_labelprint_flag) {
		this.ls_labelprint_flag = ls_labelprint_flag;
	}

	public String getIsOrdered() {
		return isOrdered;
	}

	public void setIsOrdered(String isOrdered) {
		this.isOrdered = isOrdered;
	}

	public double getNum_each_each_num() {
		return num_each_each_num;
	}

	public void setNum_each_each_num(double num_each_each_num) {
		this.num_each_each_num = num_each_each_num;
	}

	public double getNum_each_each_price() {
		return num_each_each_price;
	}

	public void setNum_each_each_price(double num_each_each_price) {
		this.num_each_each_price = num_each_each_price;
	}

	public DwOrderItem(String ch_dishno, String vch_dishname, String ch_unitno,
			String ch_unitname, double num_price, double num_price_org,
			double num_price_add, double num_num, double num_back,
			String vch_back_operID, String dt_back_operdate, int int_discount,
			String vch_dis_operID, String dt_dis_operdate, String vch_memberno,
			String ch_consult, String ch_printflag, String vch_print_memo,
			String ch_suitflag, String ch_suitno, String ch_specialflag,
			String ch_presentflag, String ch_discountflag,
			String vch_pre_operID, String dt_pre_operdate, String vch_operID,
			String dt_operdate, String ch_togono, String ch_payno,
			String ls_unitname, double num_each_num, double num_each_price,
			double num_warm, double blotout, String ch_curprice,
			String ch_discount, String re_pay, String groupname,
			String biggroupname, String smallgroupname, String discountflag,
			String dflag, double discount, double specialmoney,
			String outpflag, int suit_change_id, String int_flowID,
			String ch_tableno, String ch_billno, int int_id, double int_rate) {
		super();
		this.int_flowID = int_flowID;
		this.ch_billno = ch_billno;
		this.ch_tableno = ch_tableno;
		this.int_id = int_id;
		this.ch_dishno = ch_dishno;
		this.vch_dishname = vch_dishname;
		this.ch_unitno = ch_unitno;
		this.ch_unitname = ch_unitname;
		this.num_price = num_price;
		this.num_price_org = num_price_org;
		this.num_price_add = num_price_add;
		this.num_num = num_num;
		this.num_back = num_back;
		this.vch_back_operID = vch_back_operID;
		this.dt_back_operdate = dt_back_operdate;
		this.int_discount = int_discount;
		this.vch_dis_operID = vch_dis_operID;
		this.dt_dis_operdate = dt_dis_operdate;
		this.vch_memberno = vch_memberno;
		this.ch_consult = ch_consult;
		this.ch_printflag = ch_printflag;
		this.vch_print_memo = vch_print_memo;
		this.ch_suitflag = ch_suitflag;
		this.ch_suitno = ch_suitno;
		this.ch_specialflag = ch_specialflag;
		this.ch_presentflag = ch_presentflag;
		this.ch_discountflag = ch_discountflag;
		this.vch_pre_operID = vch_pre_operID;
		this.dt_pre_operdate = dt_pre_operdate;
		this.vch_operID = vch_operID;
		this.dt_operdate = dt_operdate;
		this.ch_togono = ch_togono;
		this.ch_payno = ch_payno;
		this.ls_unitname = ls_unitname;
		this.num_each_num = num_each_num;
		this.num_each_price = num_each_price;
		this.num_warm = num_warm;
		this.blotout = blotout;
		this.ch_curprice = ch_curprice;
		this.ch_discount = ch_discount;
		this.re_pay = re_pay;
		this.groupname = groupname;
		this.biggroupname = biggroupname;
		this.smallgroupname = smallgroupname;
		this.discountflag = discountflag;
		this.dflag = dflag;
		this.int_discount = discount;
		this.specialmoney = specialmoney;
		this.outpflag = outpflag;
		this.suit_change_id = suit_change_id;
		this.int_rate = int_rate;
	}

	public int getSuit_change_id() {
		return suit_change_id;
	}

	public void setSuit_change_id(int suit_change_id) {
		this.suit_change_id = suit_change_id;
	}

	public String getCh_discountflag() {
		return ch_discountflag;
	}

	public void setCh_discountflag(String ch_discountflag) {
		this.ch_discountflag = ch_discountflag;
	}

	public String getOutpflag() {
		return outpflag;
	}

	public void setOutpflag(String outpflag) {
		this.outpflag = outpflag;
	}

	public String getBiggroupname() {
		return biggroupname;
	}

	public void setBiggroupname(String biggroupname) {
		this.biggroupname = biggroupname;
	}

	public String getSmallgroupname() {
		return smallgroupname;
	}

	public void setSmallgroupname(String smallgroupname) {
		this.smallgroupname = smallgroupname;
	}

	public double getSpecialmoney() {
		return specialmoney;
	}

	public void setSpecialmoney(double specialmoney) {
		this.specialmoney = specialmoney;
	}

	public String getDflag() {
		return dflag;
	}

	public void setDflag(String dflag) {
		this.dflag = dflag;
	}

	public String getDiscountflag() {
		return discountflag;
	}

	public void setDiscountflag(String discountflag) {
		this.discountflag = discountflag;
	}

	public String getCh_unitname() {
		return ch_unitname;
	}

	public void setCh_unitname(String ch_unitname) {
		this.ch_unitname = ch_unitname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getRe_pay() {
		return re_pay;
	}

	public void setRe_pay(String re_pay) {
		this.re_pay = re_pay;
	}

	public String getCh_discount() {
		return ch_discount;
	}

	public void setCh_discount(String ch_discount) {
		this.ch_discount = ch_discount;
	}

	public String getCh_curprice() {
		return ch_curprice;
	}

	public void setCh_curprice(String ch_curprice) {
		this.ch_curprice = ch_curprice;
	}

	public double getBlotout() {
		return blotout;
	}

	public void setBlotout(double blotout) {
		this.blotout = blotout;
	}

	public double getNum_warm() {
		return num_warm;
	}

	public void setNum_warm(double num_warm) {
		this.num_warm = num_warm;
	}

	public double getNum_each_price() {
		return num_each_price;
	}

	public void setNum_each_price(double num_each_price) {
		this.num_each_price = num_each_price;
	}

	public double getNum_each_num() {
		return num_each_num;
	}

	public void setNum_each_num(double num_each_num) {
		this.num_each_num = num_each_num;
	}

	public String getLs_unitname() {
		return ls_unitname;
	}

	public void setLs_unitname(String ls_unitname) {
		this.ls_unitname = ls_unitname;
	}

	public String getInt_flowID() {
		return int_flowID;
	}

	public void setInt_flowID(String int_flowID) {
		this.int_flowID = int_flowID;
	}

	public String getCh_billno() {
		return ch_billno;
	}

	public void setCh_billno(String ch_billno) {
		this.ch_billno = ch_billno;
	}

	public String getCh_tableno() {
		return ch_tableno;
	}

	public void setCh_tableno(String ch_tableno) {
		this.ch_tableno = ch_tableno;
	}

	public int getInt_id() {
		return int_id;
	}

	public void setInt_id(int int_id) {
		this.int_id = int_id;
	}

	public String getCh_dishno() {
		return ch_dishno;
	}

	public void setCh_dishno(String ch_dishno) {
		this.ch_dishno = ch_dishno;
	}

	public String getVch_dishname() {
		return vch_dishname;
	}

	public void setVch_dishname(String vch_dishname) {
		this.vch_dishname = vch_dishname;
	}

	public String getCh_unitno() {
		return ch_unitno;
	}

	public void setCh_unitno(String ch_unitno) {
		this.ch_unitno = ch_unitno;
	}

	public double getNum_price() {
		return num_price;
	}

	public void setNum_price(double num_price) {
		this.num_price = num_price;
	}

	public double getNum_price_org() {
		return num_price_org;
	}

	public void setNum_price_org(double num_price_org) {
		this.num_price_org = num_price_org;
	}

	public double getNum_price_add() {
		return num_price_add;
	}

	public void setNum_price_add(double num_price_add) {
		this.num_price_add = num_price_add;
	}

	public double getNum_num() {
		return num_num;
	}

	public void setNum_num(double num_num) {
		this.num_num = num_num;
	}

	public double getNum_back() {
		return num_back;
	}

	public void setNum_back(double num_back) {
		this.num_back = num_back;
	}

	public String getVch_back_operID() {
		return vch_back_operID;
	}

	public void setVch_back_operID(String vch_back_operID) {
		this.vch_back_operID = vch_back_operID;
	}

	public String getDt_back_operdate() {
		return dt_back_operdate;
	}

	public void setDt_back_operdate(String dt_back_operdate) {
		this.dt_back_operdate = dt_back_operdate;
	}

	public double getInt_discount() {
		return int_discount;
	}

	public void setInt_discount(double int_discount) {
		this.int_discount = int_discount;
	}

	public String getVch_dis_operID() {
		return vch_dis_operID;
	}

	public void setVch_dis_operID(String vch_dis_operID) {
		this.vch_dis_operID = vch_dis_operID;
	}

	public String getDt_dis_operdate() {
		return dt_dis_operdate;
	}

	public void setDt_dis_operdate(String dt_dis_operdate) {
		this.dt_dis_operdate = dt_dis_operdate;
	}

	public String getVch_memberno() {
		return vch_memberno;
	}

	public void setVch_memberno(String vch_memberno) {
		this.vch_memberno = vch_memberno;
	}

	public String getCh_consult() {
		return ch_consult;
	}

	public void setCh_consult(String ch_consult) {
		this.ch_consult = ch_consult;
	}

	public String getCh_printflag() {
		return ch_printflag;
	}

	public void setCh_printflag(String ch_printflag) {
		this.ch_printflag = ch_printflag;
	}

	public String getVch_print_memo() {
		return vch_print_memo;
	}

	public void setVch_print_memo(String vch_print_memo) {
		this.vch_print_memo = vch_print_memo;
	}

	public String getCh_suitflag() {
		return ch_suitflag;
	}

	public void setCh_suitflag(String ch_suitflag) {
		this.ch_suitflag = ch_suitflag;
	}

	public String getCh_suitno() {
		return ch_suitno;
	}

	public void setCh_suitno(String ch_suitno) {
		this.ch_suitno = ch_suitno;
	}

	public String getCh_specialflag() {
		return ch_specialflag;
	}

	public void setCh_specialflag(String ch_specialflag) {
		this.ch_specialflag = ch_specialflag;
	}

	public String getCh_presentflag() {
		return ch_presentflag;
	}

	public void setCh_presentflag(String ch_presentflag) {
		this.ch_presentflag = ch_presentflag;
	}

	public String getVch_pre_operID() {
		return vch_pre_operID;
	}

	public void setVch_pre_operID(String vch_pre_operID) {
		this.vch_pre_operID = vch_pre_operID;
	}

	public String getDt_pre_operdate() {
		return dt_pre_operdate;
	}

	public void setDt_pre_operdate(String dt_pre_operdate) {
		this.dt_pre_operdate = dt_pre_operdate;
	}

	public String getVch_operID() {
		return vch_operID;
	}

	public void setVch_operID(String vch_operID) {
		this.vch_operID = vch_operID;
	}

	public String getDt_operdate() {
		return dt_operdate;
	}

	public void setDt_operdate(String dt_operdate) {
		this.dt_operdate = dt_operdate;
	}

	public String getCh_togono() {
		return ch_togono;
	}

	public void setCh_togono(String ch_togono) {
		this.ch_togono = ch_togono;
	}

	public String getCh_payno() {
		return ch_payno;
	}

	public void setCh_payno(String ch_payno) {
		this.ch_payno = ch_payno;
	}

	public DwOrderItem(String int_flowID, String ch_billno, String ch_tableno,
			int int_id, String ch_dishno, String vch_dishname,
			String ch_unitno, double num_price, double num_price_org,
			double num_price_add, double num_num, double num_back,
			String vch_back_operID, String dt_back_operdate, int int_discount,
			String vch_dis_operID, String dt_dis_operdate, String vch_memberno,
			String ch_consult, String ch_printflag, String vch_print_memo,
			String ch_suitflag, String ch_suitno, String ch_specialflag,
			String ch_presentflag, String vch_pre_operID,
			String dt_pre_operdate, String vch_operID, String dt_operdate,
			String ch_togono, String ch_payno, double int_rate) {
		super();
		this.int_flowID = int_flowID;
		this.ch_billno = ch_billno;
		this.ch_tableno = ch_tableno;
		this.int_id = int_id;
		this.ch_dishno = ch_dishno;
		this.vch_dishname = vch_dishname;
		this.ch_unitno = ch_unitno;
		this.num_price = num_price;
		this.num_price_org = num_price_org;
		this.num_price_add = num_price_add;
		this.num_num = num_num;
		this.num_back = num_back;
		this.vch_back_operID = vch_back_operID;
		this.dt_back_operdate = dt_back_operdate;
		this.int_discount = int_discount;
		this.vch_dis_operID = vch_dis_operID;
		this.dt_dis_operdate = dt_dis_operdate;
		this.vch_memberno = vch_memberno;
		this.ch_consult = ch_consult;
		this.ch_printflag = ch_printflag;
		this.vch_print_memo = vch_print_memo;
		this.ch_suitflag = ch_suitflag;
		this.ch_suitno = ch_suitno;
		this.ch_specialflag = ch_specialflag;
		this.ch_presentflag = ch_presentflag;
		this.vch_pre_operID = vch_pre_operID;
		this.dt_pre_operdate = dt_pre_operdate;
		this.vch_operID = vch_operID;
		this.dt_operdate = dt_operdate;
		this.ch_togono = ch_togono;
		this.ch_payno = ch_payno;
		this.int_rate = int_rate;
	}

	public DwOrderItem() {
		super();
	}

	public DwOrderItem(String int_flowID, String ch_billno, String ch_tableno,
			int int_id, String ch_dishno, String vch_dishname,
			String ch_unitno, double num_price, double num_price_org,
			double num_price_add, double num_num, double num_back,
			String vch_back_operID, String dt_back_operdate, int int_discount,
			String vch_dis_operID, String dt_dis_operdate, String vch_memberno,
			String ch_consult, String ch_printflag, String vch_print_memo,
			String ch_suitflag, String ch_suitno, String ch_specialflag,
			String ch_presentflag, String vch_pre_operID,
			String dt_pre_operdate, String vch_operID, String dt_operdate,
			String ch_togono, String ch_payno, String ls_unitname,
			double int_rate) {
		super();
		this.int_flowID = int_flowID;
		this.ch_billno = ch_billno;
		this.ch_tableno = ch_tableno;
		this.int_id = int_id;
		this.ch_dishno = ch_dishno;
		this.vch_dishname = vch_dishname;
		this.ch_unitno = ch_unitno;
		this.num_price = num_price;
		this.num_price_org = num_price_org;
		this.num_price_add = num_price_add;
		this.num_num = num_num;
		this.num_back = num_back;
		this.vch_back_operID = vch_back_operID;
		this.dt_back_operdate = dt_back_operdate;
		this.int_discount = int_discount;
		this.vch_dis_operID = vch_dis_operID;
		this.dt_dis_operdate = dt_dis_operdate;
		this.vch_memberno = vch_memberno;
		this.ch_consult = ch_consult;
		this.ch_printflag = ch_printflag;
		this.vch_print_memo = vch_print_memo;
		this.ch_suitflag = ch_suitflag;
		this.ch_suitno = ch_suitno;
		this.ch_specialflag = ch_specialflag;
		this.ch_presentflag = ch_presentflag;
		this.vch_pre_operID = vch_pre_operID;
		this.dt_pre_operdate = dt_pre_operdate;
		this.vch_operID = vch_operID;
		this.dt_operdate = dt_operdate;
		this.ch_togono = ch_togono;
		this.ch_payno = ch_payno;
		this.ls_unitname = ls_unitname;
		this.int_rate = int_rate;
	}

	public DwOrderItem(String ch_billno, String ch_tableno, int int_id,
			String ch_dishno, double num_price, double num_price_org,
			double num_price_add, double num_num, double num_back,
			String vch_back_operID, String dt_back_operdate, int int_discount,
			String vch_dis_operID, String dt_dis_operdate, String vch_memberno,
			String ch_consult, String printflag, String vch_print_memo,
			String ch_suitflag, String ch_suitno, String ch_specialflag,
			String ch_presentflag, String vch_pre_operID,
			String dt_pre_operdate, String vch_operID, String dt_operdate,
			String ch_togono, String vch_dishname, String ls_unitname,
			double num_each_price, double num_each_num, String ch_discount,
			String ch_curprice, String int_flowID, String biggroupname,
			String smallgroupname, String vch_printer, String vch_outprint,
			double int_rate) {
		super();

		if (ch_billno == null)
			ch_billno = "";

		this.ch_billno = ch_billno.trim();

		if (ch_tableno == null)
			ch_tableno = "";

		this.ch_tableno = ch_tableno.trim();
		this.int_id = int_id;

		if (ch_dishno == null)
			ch_dishno = "";

		this.ch_dishno = ch_dishno.trim();

		this.num_price = num_price;
		this.num_price_org = num_price_org;
		this.num_price_add = num_price_add;
		this.num_num = num_num;
		this.num_back = num_back;

		if (vch_back_operID == null)
			vch_back_operID = "";

		this.vch_back_operID = vch_back_operID.trim();

		if (dt_back_operdate == null)
			dt_back_operdate = "";

		this.dt_back_operdate = dt_back_operdate.trim();
		this.int_discount = int_discount;

		if (vch_dis_operID == null)
			vch_dis_operID = "";

		this.vch_dis_operID = vch_dis_operID.trim();

		if (dt_dis_operdate == null)
			dt_dis_operdate = "";

		this.dt_dis_operdate = dt_dis_operdate.trim();

		if (vch_memberno == null)
			vch_memberno = "";

		this.vch_memberno = vch_memberno.trim();

		if (ch_consult == null)
			ch_consult = "";

		this.ch_consult = ch_consult.trim();

		if (printflag == null)
			printflag = "";

		this.printflag = printflag.trim();

		if (vch_print_memo == null)
			vch_print_memo = "";

		this.vch_print_memo = vch_print_memo.trim();

		if (ch_suitflag == null)
			ch_suitflag = "";

		this.ch_suitflag = ch_suitflag.trim();

		if (ch_suitno == null)
			ch_suitno = "";

		this.ch_suitno = ch_suitno.trim();

		if (ch_specialflag == null)
			ch_specialflag = "";

		this.ch_specialflag = ch_specialflag.trim();

		if (ch_presentflag == null)
			ch_presentflag = "";

		this.ch_presentflag = ch_presentflag.trim();

		if (vch_pre_operID == null)
			vch_pre_operID = "";

		this.vch_pre_operID = vch_pre_operID.trim();

		if (dt_pre_operdate == null)
			dt_pre_operdate = "";

		this.dt_pre_operdate = dt_pre_operdate.trim();

		if (vch_operID == null)
			vch_operID = "";

		this.vch_operID = vch_operID.trim();

		if (dt_operdate == null)
			dt_operdate = "";

		this.dt_operdate = dt_operdate.trim();

		if (ch_togono == null)
			ch_togono = "";

		this.ch_togono = ch_togono.trim();

		if (vch_dishname == null)
			vch_dishname = "";

		this.vch_dishname = vch_dishname.trim();

		if (ls_unitname == null)
			ls_unitname = "";

		this.ls_unitname = ls_unitname.trim();

		this.num_each_num = num_each_num;
		this.num_each_price = num_each_price;

		if (ch_discount == null)
			ch_discount = "";

		this.ch_discount = ch_discount.trim();

		if (ch_curprice == null)
			ch_curprice = "";

		this.ch_curprice = ch_curprice.trim();

		this.int_flowID = int_flowID;

		if (biggroupname == null)
			biggroupname = "";

		this.biggroupname = biggroupname.trim();

		if (smallgroupname == null)
			smallgroupname = "";
		this.smallgroupname = smallgroupname.trim();

		if (vch_printer == null)
			vch_printer = "";
		this.vch_printer = vch_printer.trim();

		if (vch_outprint == null)
			vch_outprint = "";
		this.vch_outprint = vch_outprint.trim();

		if (outpflag == null)
			outpflag = "";
		this.outpflag = outpflag.trim();

		this.int_rate = int_rate;

	}

	public DwOrderItem(String ch_billno, String ch_tableno, int int_id,
			String ch_dishno, double num_price, double num_price_org,
			double num_price_add, double num_num, double num_back,
			String vch_back_operID, String dt_back_operdate, int int_discount,
			String vch_dis_operID, String dt_dis_operdate, String vch_memberno,
			String ch_consult, String printflag, String vch_print_memo,
			String ch_suitflag, String ch_suitno, String ch_specialflag,
			String ch_presentflag, String vch_pre_operID,
			String dt_pre_operdate, String vch_operID, String dt_operdate,
			String ch_togono, String vch_dishname, String ls_unitname,
			double num_each_price, double num_each_num, String ch_discount,
			String ch_curprice, String int_flowID, String biggroupname,
			String smallgroupname, String vch_printer, String vch_outprint,
			String outpflag, String ch_outprintfalg, String ch_printflag,
			double int_rate) {
		super();

		if (ch_billno == null)
			ch_billno = "";

		this.ch_billno = ch_billno.trim();

		if (ch_tableno == null)
			ch_tableno = "";

		this.ch_tableno = ch_tableno.trim();
		this.int_id = int_id;

		if (ch_dishno == null)
			ch_dishno = "";

		this.ch_dishno = ch_dishno.trim();

		this.num_price = num_price;
		this.num_price_org = num_price_org;
		this.num_price_add = num_price_add;
		this.num_num = num_num;
		this.num_back = num_back;

		if (vch_back_operID == null)
			vch_back_operID = "";

		this.vch_back_operID = vch_back_operID.trim();

		if (dt_back_operdate == null)
			dt_back_operdate = "";

		this.dt_back_operdate = dt_back_operdate.trim();
		this.int_discount = int_discount;

		if (vch_dis_operID == null)
			vch_dis_operID = "";

		this.vch_dis_operID = vch_dis_operID.trim();

		if (dt_dis_operdate == null)
			dt_dis_operdate = "";

		this.dt_dis_operdate = dt_dis_operdate.trim();

		if (vch_memberno == null)
			vch_memberno = "";

		this.vch_memberno = vch_memberno.trim();

		if (ch_consult == null)
			ch_consult = "";

		this.ch_consult = ch_consult.trim();

		if (printflag == null)
			printflag = "";

		this.printflag = printflag.trim();

		if (vch_print_memo == null)
			vch_print_memo = "";

		this.vch_print_memo = vch_print_memo.trim();

		if (ch_suitflag == null)
			ch_suitflag = "";

		this.ch_suitflag = ch_suitflag.trim();

		if (ch_suitno == null)
			ch_suitno = "";

		this.ch_suitno = ch_suitno.trim();

		if (ch_specialflag == null)
			ch_specialflag = "";

		this.ch_specialflag = ch_specialflag.trim();

		if (ch_presentflag == null)
			ch_presentflag = "";

		this.ch_presentflag = ch_presentflag.trim();

		if (vch_pre_operID == null)
			vch_pre_operID = "";

		this.vch_pre_operID = vch_pre_operID.trim();

		if (dt_pre_operdate == null)
			dt_pre_operdate = "";

		this.dt_pre_operdate = dt_pre_operdate.trim();

		if (vch_operID == null)
			vch_operID = "";

		this.vch_operID = vch_operID.trim();

		if (dt_operdate == null)
			dt_operdate = "";

		this.dt_operdate = dt_operdate.trim();

		if (ch_togono == null)
			ch_togono = "";

		this.ch_togono = ch_togono.trim();

		if (vch_dishname == null)
			vch_dishname = "";

		this.vch_dishname = vch_dishname.trim();

		if (ls_unitname == null)
			ls_unitname = "";

		this.ls_unitname = ls_unitname.trim();

		this.num_each_num = num_each_num;
		this.num_each_price = num_each_price;

		if (ch_discount == null)
			ch_discount = "";

		this.ch_discount = ch_discount.trim();

		if (ch_curprice == null)
			ch_curprice = "";

		this.ch_curprice = ch_curprice.trim();

		this.int_flowID = int_flowID;

		if (biggroupname == null)
			biggroupname = "";

		this.biggroupname = biggroupname.trim();

		if (smallgroupname == null)
			smallgroupname = "";
		this.smallgroupname = smallgroupname.trim();

		if (vch_printer == null)
			vch_printer = "";
		this.vch_printer = vch_printer.trim();

		if (vch_outprint == null)
			vch_outprint = "";
		this.vch_outprint = vch_outprint.trim();

		if (outpflag == null)
			outpflag = "";
		this.outpflag = outpflag.trim();

		if (ch_outprintfalg == null)
			ch_outprintfalg = "";

		this.ch_outprintfalg = ch_outprintfalg.trim();

		if (ch_printflag == null)
			ch_printflag = "";

		this.ch_printflag = ch_printflag.trim();

		this.int_rate = int_rate;

	}

	public DwOrderItem(String ch_billno, String ch_tableno, int int_id,
			String ch_dishno, double num_price, double num_price_org,
			double num_price_add, double num_num, double num_back,
			String vch_back_operID, String dt_back_operdate, int int_discount,
			String vch_dis_operID, String dt_dis_operdate, String vch_memberno,
			String ch_consult, String printflag, String vch_print_memo,
			String ch_suitflag, String ch_suitno, String ch_specialflag,
			String ch_presentflag, String vch_pre_operID,
			String dt_pre_operdate, String vch_operID, String dt_operdate,
			String ch_togono, String vch_dishname, String ls_unitname,
			double num_each_price, double num_each_num, String ch_discount,
			String ch_curprice, String int_flowID, String biggroupname,
			String smallgroupname, double int_rate) {
		super();

		if (ch_billno == null)
			ch_billno = "";

		this.ch_billno = ch_billno.trim();

		if (ch_tableno == null)
			ch_tableno = "";

		this.ch_tableno = ch_tableno.trim();
		this.int_id = int_id;

		if (ch_dishno == null)
			ch_dishno = "";

		this.ch_dishno = ch_dishno.trim();

		this.num_price = num_price;
		this.num_price_org = num_price_org;
		this.num_price_add = num_price_add;
		this.num_num = num_num;
		this.num_back = num_back;

		if (vch_back_operID == null)
			vch_back_operID = "";

		this.vch_back_operID = vch_back_operID.trim();

		if (dt_back_operdate == null)
			dt_back_operdate = "";

		this.dt_back_operdate = dt_back_operdate.trim();
		this.int_discount = int_discount;

		if (vch_dis_operID == null)
			vch_dis_operID = "";

		this.vch_dis_operID = vch_dis_operID.trim();

		if (dt_dis_operdate == null)
			dt_dis_operdate = "";

		this.dt_dis_operdate = dt_dis_operdate.trim();

		if (vch_memberno == null)
			vch_memberno = "";

		this.vch_memberno = vch_memberno.trim();

		if (ch_consult == null)
			ch_consult = "";

		this.ch_consult = ch_consult.trim();

		if (printflag == null)
			printflag = "";

		this.printflag = printflag.trim();

		if (vch_print_memo == null)
			vch_print_memo = "";

		this.vch_print_memo = vch_print_memo.trim();

		if (ch_suitflag == null)
			ch_suitflag = "";

		this.ch_suitflag = ch_suitflag.trim();

		if (ch_suitno == null)
			ch_suitno = "";

		this.ch_suitno = ch_suitno.trim();

		if (ch_specialflag == null)
			ch_specialflag = "";

		this.ch_specialflag = ch_specialflag.trim();

		if (ch_presentflag == null)
			ch_presentflag = "";

		this.ch_presentflag = ch_presentflag.trim();

		if (vch_pre_operID == null)
			vch_pre_operID = "";

		this.vch_pre_operID = vch_pre_operID.trim();

		if (dt_pre_operdate == null)
			dt_pre_operdate = "";

		this.dt_pre_operdate = dt_pre_operdate.trim();

		if (vch_operID == null)
			vch_operID = "";

		this.vch_operID = vch_operID.trim();

		if (dt_operdate == null)
			dt_operdate = "";

		this.dt_operdate = dt_operdate.trim();

		if (ch_togono == null)
			ch_togono = "";

		this.ch_togono = ch_togono.trim();

		if (vch_dishname == null)
			vch_dishname = "";

		this.vch_dishname = vch_dishname.trim();

		if (ls_unitname == null)
			ls_unitname = "";

		this.ls_unitname = ls_unitname.trim();

		this.num_each_num = num_each_num;
		this.num_each_price = num_each_price;

		if (ch_discount == null)
			ch_discount = "";

		this.ch_discount = ch_discount.trim();

		if (ch_curprice == null)
			ch_curprice = "";

		this.ch_curprice = ch_curprice.trim();

		this.int_flowID = int_flowID;

		if (biggroupname == null)
			biggroupname = "";

		this.biggroupname = biggroupname.trim();

		if (smallgroupname == null)
			smallgroupname = "";
		this.smallgroupname = smallgroupname.trim();

		this.int_rate = int_rate;

	}

	public DwOrderItem(String ls_labelprint_flag, String biggroupname,
			String smallgroupname, String ch_dishno, String vch_barcode,
			String ch_printflag, String ch_outprintfalg, String vch_dishname,
			String ls_unitname, String ch_suitno, double num_num,
			double num_each_num, double num_each_each_num,
			double num_each_each_price, double num_each_price,
			double num_price_org, double num_price, String ch_suitflag,
			String ch_curprice, String ch_discount, String ch_weight,
			double num_back, double num_price_add, int int_discount,
			double blotout, String ch_presentflag, String ch_consult,
			String vch_printer, String vch_outprint, double int_rate) {

		super();
		this.ls_labelprint_flag = ls_labelprint_flag;
		this.biggroupname = biggroupname;
		this.smallgroupname = smallgroupname;
		this.ch_dishno = ch_dishno;
		this.ch_printflag = ch_printflag;
		this.ch_outprintfalg = ch_outprintfalg;
		this.vch_dishname = vch_dishname;
		this.vch_barcode = vch_barcode;
		this.ls_unitname = ls_unitname;
		this.ch_suitno = ch_suitno;
		this.num_num = num_num;
		this.num_each_num = num_each_num;
		this.num_each_each_num = num_each_each_num;
		this.num_each_each_price = num_each_each_price;
		this.num_each_price = num_each_price;
		this.num_price_org = num_price_org;
		this.num_price = num_price;
		this.ch_suitflag = ch_suitflag;
		this.ch_curprice = ch_curprice;
		this.ch_discount = ch_discount;
		this.num_back = num_back;
		this.num_price_add = num_price_add;
		this.int_discount = int_discount;
		this.ch_weight = ch_weight;
		this.blotout = blotout;
		this.ch_presentflag = ch_presentflag;
		this.ch_consult = ch_consult;
		this.vch_printer = vch_printer;
		this.vch_outprint = vch_outprint;
		this.int_rate = int_rate;

	}

	public DwOrderItem(String ls_labelprint_flag, String biggroupname,
			String smallgroupname, String ch_dishno, String ch_printflag,
			String outpflag, String vch_dishname, String ls_unitname,
			String ch_suitno, double num_num, double num_each_num,
			double num_each_each_num, double num_each_each_price,
			double num_each_price, double num_price_org, double num_price,
			String ch_suitflag, String ch_curprice, String ch_discount,
			double num_back, double num_price_add, int int_discount,
			double blotout, String ch_presentflag, String ch_consult,
			String vch_printer, String vch_outprint, String vch_spell,
			double int_rate) {

		super();
		this.ls_labelprint_flag = ls_labelprint_flag;
		this.biggroupname = biggroupname;
		this.smallgroupname = smallgroupname;
		this.ch_dishno = ch_dishno;
		this.ch_printflag = ch_printflag;
		this.outpflag = outpflag;
		this.vch_dishname = vch_dishname;
		this.ls_unitname = ls_unitname;
		this.ch_suitno = ch_suitno;
		this.num_num = num_num;
		this.num_each_num = num_each_num;
		this.num_each_each_num = num_each_each_num;
		this.num_each_each_price = num_each_each_price;
		this.num_each_price = num_each_price;
		this.num_price_org = num_price_org;
		this.num_price = num_price;
		this.ch_suitflag = ch_suitflag;
		this.ch_curprice = ch_curprice;
		this.ch_discount = ch_discount;
		this.num_back = num_back;
		this.num_price_add = num_price_add;
		this.int_discount = int_discount;
		this.blotout = blotout;
		this.ch_presentflag = ch_presentflag;
		this.ch_consult = ch_consult;
		this.vch_printer = vch_printer;
		this.vch_outprint = vch_outprint;
		this.vch_spell = vch_spell;
		this.int_rate = int_rate;

	}

	public DwOrderItem(String ls_labelprint_flag, String biggroupname,
			String smallgroupname, String ch_dishno, String ch_printflag,
			String ch_outprintfalg, String vch_dishname, String ls_unitname,
			String ch_suitno, double num_num, double num_each_num,
			double num_each_each_num, double num_each_each_price,
			double num_each_price, double num_price_org, double num_price,
			String ch_suitflag, String ch_curprice, String ch_discount,
			double num_back, double num_price_add, int int_discount,
			double blotout, String ch_presentflag, String ch_consult,
			double num_warm, String vch_operID, String dt_operdate,
			String ch_specialflag, String vch_printer, String vch_outprint,
			double int_rate) {

		super();
		this.ls_labelprint_flag = ls_labelprint_flag;
		this.biggroupname = biggroupname;
		this.smallgroupname = smallgroupname;
		this.ch_dishno = ch_dishno;
		this.ch_printflag = ch_printflag;
		this.ch_outprintfalg = ch_outprintfalg;
		this.vch_dishname = vch_dishname;
		this.ls_unitname = ls_unitname;
		this.ch_suitno = ch_suitno;
		this.num_num = num_num;
		this.num_each_num = num_each_num;
		this.num_each_each_num = num_each_each_num;
		this.num_each_each_price = num_each_each_price;
		this.num_each_price = num_each_price;
		this.num_price_org = num_price_org;
		this.num_price = num_price;
		this.ch_suitflag = ch_suitflag;
		this.ch_curprice = ch_curprice;
		this.ch_discount = ch_discount;
		this.num_back = num_back;
		this.num_price_add = num_price_add;
		this.int_discount = int_discount;
		this.blotout = blotout;
		this.ch_presentflag = ch_presentflag;
		this.ch_consult = ch_consult;

		this.num_warm = num_warm;
		this.vch_operID = vch_operID;
		this.dt_operdate = dt_operdate;
		this.ch_specialflag = ch_specialflag;

		this.vch_printer = vch_printer;
		this.vch_outprint = vch_outprint;

		this.int_rate = int_rate;

	}

	public DwOrderItem(String ls_labelprint_flag) {

		super();
		this.ls_labelprint_flag = ls_labelprint_flag;

	}

	public DwOrderItem(String ls_labelprint_flag, String biggroupname,
			String smallgroupname, String ch_dishno, String ch_printflag,
			String ch_outprintfalg, String vch_dishname, String ch_unitno,
			String ch_suitno, double int_discount, double blotout,
			double num_each_num, double num_each_each_num, double num_num,
			double num_back, double num_each_each_price, double num_each_price,
			double num_price_org, double num_price, double num_price_add,
			String ch_presentflag, String ch_consult, String ch_suitflag,
			String ch_curprice, String ch_discount, String ch_specialflag,
			String vch_operID, String dt_operdate, String ls_unitname,
			String vch_printer, String vch_outprint, double int_rate,
			double num_price_save) {

		this.num_price_save = num_price_save;
		this.ls_labelprint_flag = ls_labelprint_flag;
		this.biggroupname = biggroupname;
		this.smallgroupname = smallgroupname;
		this.ch_dishno = ch_dishno;
		this.ch_printflag = ch_printflag;
		this.ch_outprintfalg = ch_outprintfalg;
		this.vch_dishname = vch_dishname;
		this.ch_unitno = ch_unitno;
		this.ch_suitno = ch_suitno;
		this.int_discount = int_discount;
		this.blotout = blotout;
		this.num_each_num = num_each_num;
		this.num_each_each_num = num_each_each_num;
		this.num_num = num_num;
		this.num_back = num_back;
		this.num_each_each_price = num_each_each_price;
		this.num_each_price = num_each_price;
		this.num_price_org = num_price_org;
		this.num_price = num_price;
		this.num_price_add = num_price_add;
		this.ch_presentflag = ch_presentflag;
		this.ch_consult = ch_consult;
		this.ch_suitflag = ch_suitflag;
		this.ch_curprice = ch_curprice;
		this.ch_discount = ch_discount;
		this.ch_specialflag = ch_specialflag;
		this.vch_operID = vch_operID;
		this.dt_operdate = dt_operdate;
		this.ls_unitname = ls_unitname;
		this.vch_printer = vch_printer;
		this.vch_outprint = vch_outprint;
		this.int_rate = int_rate;

	}

	public DwOrderItem(String ch_bookno, int int_id, String vch_dishname,
			String vch_spec, int num_num, int num_price, String vch_print_memo,
			int num_price_add, String ch_suitflag, String ch_suitno,
			String int_material_rate, String ch_fixprice,
			String ch_specialflag, String ch_eatflag, double int_rate) {
		super();
		if (ch_bookno == null)
			ch_bookno = "";
		this.ch_bookno = ch_bookno.trim();

		this.int_id = int_id;
		if (vch_dishname == null)
			vch_dishname = "";
		this.vch_dishname = vch_dishname.trim();
		if (vch_spec == null)
			vch_spec = "";
		this.vch_spec = vch_spec.trim();

		this.num_num = num_num;

		this.num_price = num_price;
		if (vch_print_memo == null)
			vch_print_memo = "";
		this.vch_print_memo = vch_print_memo.trim();
		this.num_price_add = num_price_add;
		if (ch_suitflag == null)
			ch_suitflag = "";
		this.ch_suitflag = ch_suitflag.trim();
		if (ch_suitno == null)
			ch_suitno = "";
		this.ch_suitno = ch_suitno.trim();
		if (int_material_rate == null)
			int_material_rate = "";
		this.int_material_rate = int_material_rate.trim();
		if (ch_fixprice == null)
			ch_fixprice = "";
		this.ch_fixprice = ch_fixprice.trim();
		if (ch_specialflag == null)
			ch_specialflag = "";
		this.ch_specialflag = ch_specialflag.trim();
		if (ch_eatflag == null)
			ch_eatflag = "";
		this.ch_eatflag = ch_eatflag.trim();

		this.int_rate = int_rate;
	}

	public DwOrderItem(String vch_dishname, int num_num) {
		super();

		if (vch_dishname == null)
			vch_dishname = "";
		this.vch_dishname = vch_dishname.trim();

		this.num_num = num_num;
	}

	@Override
	public String toString() {
		return "DwOrderItem [int_flowID=" + int_flowID + ", ch_billno="
				+ ch_billno + ", ch_tableno=" + ch_tableno + ", int_id="
				+ int_id + ", ch_dishno=" + ch_dishno + ", vch_dishname="
				+ vch_dishname + ", ch_unitno=" + ch_unitno + ", num_price="
				+ num_price + ", num_price_org=" + num_price_org
				+ ", num_price_add=" + num_price_add + ", num_num=" + num_num
				+ ", num_back=" + num_back + ", vch_back_operID="
				+ vch_back_operID + ", dt_back_operdate=" + dt_back_operdate
				+ ", int_discount=" + int_discount + ", vch_dis_operID="
				+ vch_dis_operID + ", dt_dis_operdate=" + dt_dis_operdate
				+ ", vch_memberno=" + vch_memberno + ", ch_consult="
				+ ch_consult + ", ch_printflag=" + ch_printflag
				+ ", vch_print_memo=" + vch_print_memo + ", ch_suitflag="
				+ ch_suitflag + ", ch_suitno=" + ch_suitno
				+ ", ch_specialflag=" + ch_specialflag + ", ch_presentflag="
				+ ch_presentflag + ", vch_pre_operID=" + vch_pre_operID
				+ ", dt_pre_operdate=" + dt_pre_operdate + ", vch_operID="
				+ vch_operID + ", dt_operdate=" + dt_operdate + ", ch_togono="
				+ ch_togono + ", ch_payno=" + ch_payno + ", ls_unitname="
				+ ls_unitname + ", num_each_num=" + num_each_num + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

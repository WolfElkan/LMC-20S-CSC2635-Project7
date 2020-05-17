import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

class ExcelDate extends Date {
	public ExcelDate(long date) {
		super(date);
	}
	double Excel(int zone_offset) {
		long ms = this.getTime();
		ms += (long) 25569 * 86400 * 1000;
		ms += zone_offset;
		double excel = (double) ms;
		excel /= 86400000;
		return excel;
	}
	double Excel(double tzh) {
		int zone_offset = (int) tzh;
		zone_offset *= 3600000;
		return Excel(zone_offset);
	}
	double Excel(TimeZone zone) {
		long ms = this.getTime();
		int offset = zone.getOffset(ms);
		return Excel(offset);
	}
	double Excel() {
		Calendar local = Calendar.getInstance();
		TimeZone zone = local.getTimeZone();
		return Excel(zone);
	}
	public static void main(String[] args) {
		ExcelDate k = new ExcelDate(1589746962522L);
		// System.out.println(k.toGMTString());
		System.out.println(k.Excel());
	}
}
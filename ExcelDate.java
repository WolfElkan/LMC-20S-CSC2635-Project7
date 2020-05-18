import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class ExcelDate extends Date {

	private long epoch1900 = (long) 25569 * 86400 * 1000;
	private long epoch1904 = (long) 24107 * 86400 * 1000;

	public ExcelDate() {super();}
	public ExcelDate(long date) {super(date);}

	// public ExcelDate(Epoch sys, String data) {
	// 	double serial = Double.valueOf(data);
	// 	ExcelDate(sys, serial);
	// }
	// public ExcelDate(Epoch sys, double serial) {
	// 	serial *= 86400000;
	// 	long ms = Math.round(serial);
	// 	ExcelDate(ms);
	// }

	double Excel(long epoch) {
		Calendar local = Calendar.getInstance();
		TimeZone zone = local.getTimeZone();
		return Excel(zone, epoch);
	}
	double Excel(TimeZone zone, long epoch) {
		long ms = this.getTime();
		int offset = zone.getOffset(ms);
		return Excel(offset, epoch);
	}
	double Excel(double tzh, long epoch) {
		int zone_offset = (int) tzh;
		zone_offset *= 3600000;
		return Excel(zone_offset, epoch);
	}
	double Excel(int zone_offset, long epoch) {
		long ms = this.getTime();
		ms += epoch;
		ms += zone_offset;
		double excel = (double) ms;
		excel /= 86400000;
		return excel;
	}

	double Excel1900() {return Excel(epoch1900);}
	double Excel1900(TimeZone zone) {return Excel(zone, epoch1900);}
	double Excel1900(double tzh) {return Excel(tzh, epoch1900);}
	double Excel1900(int tzms) {return Excel(tzms, epoch1900);}

	double Excel1904() {return Excel(epoch1904);}
	double Excel1904(TimeZone zone) {return Excel(zone, epoch1904);}
	double Excel1904(double tzh) {return Excel(tzh, epoch1904);}
	double Excel1904(int tzms) {return Excel(tzms, epoch1904);}

	public String write(Epoch sys) {
		if (sys == Epoch.MCM) {
			return Double.toString(Excel1900());
		} else if (sys == Epoch.MCMIV) {
			return Double.toString(Excel1904());
		} else {
			return Long.toString(this.getTime());
		}
	}

	public static void main(String[] args) {
		ExcelDate k = new ExcelDate();
		System.out.println(k.Excel1900());
	}
}
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class ExcelDate extends Date {

	private long epoch1900 = (long) 25569 * 86400 * 1000;
	private long epoch1904 = (long) 24107 * 86400 * 1000;

	public ExcelDate() {super();}
	public ExcelDate(long date) {super(date);}

	public ExcelDate(Epoch sys, String data) {
		parseAndSet(sys, data, getTZms());
	}
	public ExcelDate(Epoch sys, String data, TimeZone zone) {
		parseAndSet(sys, data, getTZms(zone));
	}
	public ExcelDate(Epoch sys, String data, double tzh) {
		parseAndSet(sys, data, getTZms(tzh));
	}
	public ExcelDate(Epoch sys, String data, int tzms) {
		parseAndSet(sys, data, tzms);
	}

	private void parseAndSet(Epoch sys, String data, int tzms) {
		long ms;
		if (sys == Epoch.UNIX) {
			ms = Long.valueOf(data);
		} else {
			double serial = Double.valueOf(data);
			serial *= 86400000;
			ms = Math.round(serial);
			if (sys == Epoch.MCM) {
				ms -= epoch1900;
			} else if (sys == Epoch.MCMIV) {
				ms -= epoch1904;
			}
			ms -= tzms;
		}
		setTime(ms);
	}

	int getTZms() {
		Calendar local = Calendar.getInstance();
		TimeZone zone = local.getTimeZone();
		return getTZms(zone);
	}
	int getTZms(TimeZone zone) {
		long ms = this.getTime();
		return zone.getOffset(ms);
	}
	int getTZms(double tzh) {
		int zone_offset = (int) tzh;
		zone_offset *= 3600000;
		return zone_offset;
	}

	double Excel(long epoch) {
		return Excel(getTZms(), epoch);
	}
	double Excel(TimeZone zone, long epoch) {
		return Excel(getTZms(zone), epoch);
	}
	double Excel(double tzh, long epoch) {
		return Excel(getTZms(tzh), epoch);
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
		ExcelDate k = new ExcelDate(Epoch.MCM, "43970.515981006945");
		System.out.println(k);
		System.out.println(k.Excel1900());
	}
}
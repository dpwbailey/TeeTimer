package sample;

public class Appointment extends intervalScheduler {

  Appointment appointment = new Appointment();
  private String name = "Default Name";
  private String preferredTime = "Default Preferred Time";
  private String averageRoundDuration = "Default Round Duration";
  //need to convert duration string from 1:00:00 format to a numeric value, maybe use milliseconds?

  private static final String csvPath = "Activities.csv";

  public Appointment() {
//default constructor
  }

  public Appointment(Appointment appointment, String name, String preferredTime,
      String averageRoundDuration) {
    this.appointment = appointment;
    this.name = name;
    this.preferredTime = preferredTime;
    this.averageRoundDuration = averageRoundDuration;
  }

  public Appointment getAppointment() {
    return appointment;
  }

  public void setAppointment(Appointment appointment) {
    this.appointment = appointment;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPreferredTime() {
    return preferredTime;
  }

  public void setPreferredTime(String preferredTime) {
    this.preferredTime = preferredTime;
  }

  public String getAverageRoundDuration() {
    return averageRoundDuration;
  }

  public void setAverageRoundDuration(String averageRoundDuration) {
    this.averageRoundDuration = averageRoundDuration;
  }

  public static String getCsvPath() {
    return csvPath;
  }

  public static void main(String[] args) {
    try {

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

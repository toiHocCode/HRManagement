package be.obj;

import be.Cadres;
import be.CadresType;

public class Staff extends Cadres {
    public Staff(String name, int id, String dob, CadresType type, int papers, int teachingHours, int serveHours) {
        super(name, id, dob, type, papers, teachingHours, serveHours);
    }

    @Override
    public String getFormatValue() {
        return getName() +","+ getId() +","+ getDob() +","+ getType() +","+ getPapers() +","+ getTeach() +","+ getServe() +",";
    }
}

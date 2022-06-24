package be;

public abstract class Cadres {

    public static double _STTEACH = 10.0;
    public static  double _STSERVE = 10.0;
    public static double _STPAPERS = 5.0;

    private String name;
    private int id;
    private String dob;
    private CadresType type;

    public Cadres(String name, int id, String dob, CadresType type, int papers, int teach, int serve) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.type = type;
        this.papers = papers;
        this.teach = teach;
        this.serve = serve;
    }

    private int papers;
    private int teach;
    private int serve;

    public abstract String getFormatValue();

    public boolean isRewarded() {
        double teachRate = teach *1.0/Cadres._STTEACH;
        double serveRate = serve *1.0/Cadres._STSERVE;
        return (teachRate >= 1.2 || serveRate >= 1.5 || papers - 2 >= Cadres._STPAPERS);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public CadresType getType() {
        return type;
    }

    public void setType(CadresType type) {
        this.type = type;
    }

    public int getPapers() {
        return papers;
    }

    public void setPapers(int papers) {
        this.papers = papers;
    }

    public int getTeach() {
        return teach;
    }

    public void setTeach(int teach) {
        this.teach = teach;
    }

    public int getServe() {
        return serve;
    }

    public void setServe(int serve) {
        this.serve = serve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cadres cadres = (Cadres) o;

        return id == cadres.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

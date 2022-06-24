package be;

import be.obj.Researcher;
import be.obj.Staff;
import be.obj.Teacher;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class CadresManagement {
    private static CadresManagement instance;
    private ObservableList<Cadres> listCadres;

    public static CadresManagement getInstance() {
        if (instance == null) {
            instance = new CadresManagement();
        }
        return instance;
    }

    public CadresType cadresTypeStringToObj(String type) {
        switch (type) {
            case "Teacher": return CadresType.Teacher;
            case "Staff": return CadresType.Staff;
            case "Researcher": return CadresType.Researcher;
            default: return CadresType.None;
        }
    }

    public Cadres createCadres(String name, int id, String dob, CadresType type, int papers, int teachingHours, int serveHours) {
        switch (type) {
            case Teacher: return new Teacher(name,id,dob,type,papers,teachingHours,serveHours);
            case Staff: return new Staff(name,id,dob,type,papers,teachingHours,serveHours);
            case Researcher: return new Researcher(name,id,dob,type,papers,teachingHours,serveHours);
            default:
        }
        return null;
    }

    public Cadres createCadres(String name, String id, String dob, String type, String papers, String teachingHours, String serveHours) {

        if (!isValidInput(name, id, dob, type, papers, teachingHours, serveHours)) return null;
        return createCadres(name, Integer.valueOf(id), dob, CadresManagement.getInstance().cadresTypeStringToObj(type)
                , Integer.valueOf(papers), Integer.valueOf(teachingHours), Integer.valueOf(serveHours));
    }

    public void setListCadres(ObservableList<Cadres> listCadres) {
        this.listCadres = listCadres;
    }

    public ObservableList<Cadres> getListCadres() {
        if (listCadres == null){
            listCadres = new ObservableList<Cadres>() {
                @Override
                public void addListener(ListChangeListener<? super Cadres> listener) {

                }

                @Override
                public void removeListener(ListChangeListener<? super Cadres> listener) {

                }

                @Override
                public boolean addAll(Cadres... elements) {
                    return false;
                }

                @Override
                public boolean setAll(Cadres... elements) {
                    return false;
                }

                @Override
                public boolean setAll(Collection<? extends Cadres> col) {
                    return false;
                }

                @Override
                public boolean removeAll(Cadres... elements) {
                    return false;
                }

                @Override
                public boolean retainAll(Cadres... elements) {
                    return false;
                }

                @Override
                public void remove(int from, int to) {

                }

                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<Cadres> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(Cadres cadres) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends Cadres> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, Collection<? extends Cadres> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public Cadres get(int index) {
                    return null;
                }

                @Override
                public Cadres set(int index, Cadres element) {
                    return null;
                }

                @Override
                public void add(int index, Cadres element) {

                }

                @Override
                public Cadres remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<Cadres> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<Cadres> listIterator(int index) {
                    return null;
                }

                @Override
                public List<Cadres> subList(int fromIndex, int toIndex) {
                    return null;
                }

                @Override
                public void addListener(InvalidationListener listener) {

                }

                @Override
                public void removeListener(InvalidationListener listener) {

                }
            };
            loadData();
        }
        return listCadres;
    }

    public void loadData() {
        try {
            URL resource = CadresManagement.class.getResource("/data.csv");
            String path = Paths.get(resource.toURI()).toString();
            listCadres = Utils.readCadresFromCSV(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cadres> searchByName(String name, ObservableList<Cadres> listCadres) {
        List<Cadres> list = new ArrayList<>();
        for (Cadres child : listCadres) {
            if (child.getName().equals(name)) list.add(child);
        }
        return FXCollections.observableArrayList(list);
    }

    public ObservableList<Cadres> searchByID(int id, ObservableList<Cadres> listCadres) {
        List<Cadres> list = new ArrayList<>();
        for (Cadres child : listCadres) {
            if (child.getId() == id) list.add(child);
        }
        return FXCollections.observableArrayList(list);
    }

    public ObservableList<Cadres> searchByDOB(String dob, ObservableList<Cadres> listCadres) {
        List<Cadres> list = new ArrayList<>();
        for (Cadres child : listCadres) {
            if (child.getDob().equals(dob)) list.add(child);
        }
        return FXCollections.observableArrayList(list);
    }

    public ObservableList<Cadres> searchByType(CadresType type, ObservableList<Cadres> listCadres) {
        List<Cadres> list = new ArrayList<>();
        for (Cadres child : listCadres) {
            if (child.getType().equals(type)) list.add(child);
        }
        return FXCollections.observableArrayList(list);
    }

    public ObservableList<Cadres> searchByType(String type, ObservableList<Cadres> listCadres) {
        return searchByType(CadresManagement.getInstance().cadresTypeStringToObj(type), getListCadres());
    }

    public ObservableList<Cadres> filterByPapers(int papers, ObservableList<Cadres> listCadres) {
        List<Cadres> list = new ArrayList<>();
        for (Cadres child : listCadres) {
            if (child.getPapers() <= papers) list.add(child);
        }
        return FXCollections.observableArrayList(list);
    }

    public ObservableList<Cadres> filterByTeach(int teach, ObservableList<Cadres> listCadres) {
        List<Cadres> list = new ArrayList<>();
        for (Cadres child : listCadres) {
            if (child.getTeach() >= teach) list.add(child);
        }
        return FXCollections.observableArrayList(list);
    }

    public boolean isValidInput(String name, String id, String dob, String type, String papers, String teachingHours, String serveHours) {
        return !(name.equals("") || id.equals("") || dob.equals("")
                || type.equals("") || papers.equals("") || teachingHours.equals("") || serveHours.equals(""));
    }

}

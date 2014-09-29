package edu.chl.hajo.jpf.util;

import edu.chl.hajo.jpf.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * This is used in the personList.xhtml, see <p:dataTable> tag Custom Lazy
 * Person DataModel which extends PrimeFaces LazyDataModel. For more information
 * please visit http://www.primefaces.org/showcase-labs/ui/datatableLazy.jsf
 *
 */
public class LazyPersonDataModel extends LazyDataModel<Person> {

    private static final Logger LOG = Logger.getLogger(LazyPersonDataModel.class.getName());

    // Selected Page size in the DataTable
    private int pageSize;
    // Current row index number
    private int rowIndex;
    // Total row number
    private int rowCount;
    // Service for create read update delete operations
    private final PersonRegistry reg;
    // Just tmp, to get data
    private List<Person> persons;

    public LazyPersonDataModel(PersonRegistry reg) {
        this.reg = reg;
    }

    @Override
    public List<Person> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, String> filters) {
        LOG.log(Level.INFO, "load first {0} last {1}", new Object[]{first, first + pageSize});
        persons = new ArrayList(reg.findRange(first, pageSize));
        setRowCount(reg.count());
        return persons;
    }

    /**
     * Checks if the row is available
     *
     * @return boolean
     */
    @Override
    public boolean isRowAvailable() {
        if (persons == null) {
            return false;
        }
        int index = rowIndex % pageSize;
        return index >= 0 && index < persons.size();
    }

    /**
     * Gets the user object's primary key
     *
     * @param person
     * @return
     */
    @Override
    public Object getRowKey(Person person) {
        return person.getId().toString();
    }

    /**
     * Returns the object at the specified position in list.
     *
     * @return
     */
    @Override
    public Person getRowData() {
        if (persons == null) {
            return null;
        }
        int index = rowIndex % pageSize;
        if (index > persons.size()) {
            return null;
        }
        return persons.get(index);
    }

    /**
     * Returns the object that has the row key.
     *
     * @param rowKey
     * @return
     */
    @Override
    public Person getRowData(String rowKey) {
        if (persons == null) {
            return null;
        }
        for (Person user : persons) {
            if (user.getId().toString().equals(rowKey)) {
                return user;
            }
        }
        return null;
    }

    /*
     * ===== Getters and Setters of LazyPersonDataModel fields
     */
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public void setWrappedData(Object list) {
        this.persons = (List<Person>) list;
    }

    @Override
    public Object getWrappedData() {
        return persons;
    }
}

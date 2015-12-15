package db.retail.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This interface facilitates solving Master-Detail situations, such as creation
 * of trees with root nodes with all sub-nodes list.
 *
 * @author Dobri
 * @param <Criteria> Criteria class
 * @param <MasterType> Master for which we generate list of details
 */
public interface IMasterDetail<MasterType, Criteria> {

    /**
     * Get the detail list for the master root data.
     *
     * @param master
     * @return List
     */
    public Set getDetails(MasterType master);

    /**
     * Get the map of the root (master) with its list of details
     *
     * @param criteria
     * @return
     */
    public Map<MasterType, Set> getRootNodes(Criteria criteria);

}

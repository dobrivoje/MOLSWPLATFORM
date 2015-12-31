package db.retail.interfaces;

import java.util.List;
import java.util.Map;

/**
 * This interface facilitates solving Master-Detail situations, such as creation
 * of trees with root nodes with all sub-nodes list.
 *
 * @param <ROOT_TYPE>
 * @param <CRITERIA_TYPE>
 */
public interface IMasterDetail4<ROOT_TYPE, CRITERIA_TYPE> {

    Map<ROOT_TYPE, List> getMasterDetail(CRITERIA_TYPE criteria);

}

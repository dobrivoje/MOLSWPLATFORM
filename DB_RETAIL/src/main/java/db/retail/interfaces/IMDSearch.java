package db.retail.interfaces;

import java.util.List;
import java.util.Map;

/**
 * This interface facilitates solving Master-Detail situations, such as creation
 * of trees with root nodes with all sub-nodes list.
 *
 * @author Dobri
 * @param <MasterType> Master for which we generate list of details
 * @param <SearchType>
 */
public interface IMDSearch<MasterType, SearchType> extends IMasterDetail<MasterType>, ISearch<SearchType> {

    Map<MasterType, List> getTree();
}

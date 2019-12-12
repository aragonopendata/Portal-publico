package es.aragon.base.crawler.admin.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SafeConsumer;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

import es.aragon.base.crawler.admin.constants.MVCCommandNames;
import es.aragon.base.crawler.service.permission.PagePermissionChecker;

/**
 * 
 * @author Mikel Jorge
 *
 */
public class PageManagementToolbarDisplayContext {
	
	private final PagePermissionChecker pagePermissionChecker;
	private final PortletURL currentURLObj;
	private final LiferayPortletRequest liferayPortletRequest;
	private final LiferayPortletResponse liferayPortletResponse;
	private final PortalPreferences portalPreferences;
	private final HttpServletRequest request;
	
	public PageManagementToolbarDisplayContext(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest request, PagePermissionChecker pagePermissionChecker) {

		this.pagePermissionChecker = pagePermissionChecker;
		this.liferayPortletRequest = liferayPortletRequest;
		this.liferayPortletResponse = liferayPortletResponse;
		this.request = request;
		currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

		portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);
	}
	
	/**
	 * Returns the action dropdown items for the toolbar 
	 * 
	 * @return action dropdown items
	 */
	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
				add(SafeConsumer.ignore(
						dropdownItem -> {
							dropdownItem.putData("action", "deletePages");
							dropdownItem.setIcon("times");
							String label = "delete";
							dropdownItem.setLabel(LanguageUtil.get(request, label));
							dropdownItem.setQuickAction(true);
						}));
			}
		};
	}
	
	/**
	 * Returns the action URL for the search.
	 * 
	 * @return search action URL
	 */
	public String getSearchActionURL() {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		PortletURL searchURL =  PortletURLFactoryUtil.create(request, themeDisplay.getPortletDisplay().getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		// PortletURL searchURL = liferayPortletResponse.createRenderURL();
		searchURL.setProperty("mvcRenderCommandName", MVCCommandNames.VIEW_PAGES);
		searchURL.setParameter("orderByCol", getOrderByCol());
		searchURL.setParameter("orderByType", getOrderByType());

		return searchURL.toString();
	}
	
	/**
	 * Returns the sort order column.
	 * 
	 * @return sort column
	 */
	public String getOrderByCol() {
		return ParamUtil.getString(request, "orderByCol", "pageId");
	}
	
	/**
	 * Returns the sort type (ascending / descending).
	 * 
	 * @return sort type
	 */
	public String getOrderByType() {
		return ParamUtil.getString(request, "orderByType", "asc");
	}
	
	/**
	 * Returns the creation menu for the toolbar 
	 * (plus sign on the management toolbar).
	 * 
	 * @return creation menu
	 */
	public CreationMenu getCreationMenu() {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		// Check if user has permissions to add pages
		if (!pagePermissionChecker.containsTopLevel(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), PagePermissionChecker.ADD_PAGE)) {

			return null;
		}

		// Create the menu.
		CreationMenu creationMenu = new CreationMenu();
		creationMenu.addDropdownItem(dropdownItem -> {
			dropdownItem.setHref(
				PortletURLFactoryUtil.create(request, themeDisplay.getPortletDisplay().getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE),
				// liferayPortletResponse.createRenderURL(),
				"mvcRenderCommandName", MVCCommandNames.EDIT_PAGE,
				"redirect", currentURLObj.toString());
			dropdownItem.setLabel(LanguageUtil.get(request, "add-page"));
		});

		return creationMenu;
	}
	
	/**
	 * Returns the filter menu options.
	 * 
	 * @return menu options list
	 */
	public List<DropdownItem> getFilterDropdownItems() {
		return new DropdownItemList() {
			{
				addGroup(dropdownGroupItem -> {
					dropdownGroupItem.setDropdownItems(getOrderByDropdownItems());
					dropdownGroupItem.setLabel(LanguageUtil.get(request, "order-by"));
				});
			}
		};
	}
	
	/**
	 * Return the option items for the sort column menu.
	 * 
	 * @return options list for the sort column menu
	 */
	private List<DropdownItem> getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				add(SafeConsumer.ignore(dropdownItem -> {
					dropdownItem.setActive("title".equals(getOrderByCol()));
					dropdownItem.setHref(getCurrentSortingURL(), "orderByCol", "title");
					dropdownItem.setLabel(LanguageUtil.get(request, "title"));
				}));

				add(SafeConsumer.ignore(dropdownItem -> {
					dropdownItem.setActive("pageId".equals(getOrderByCol()));
					dropdownItem.setHref(getCurrentSortingURL(), "orderByCol", "pageId");
					dropdownItem.setLabel(LanguageUtil.get(request, "pageId"));
				}));
				
				add(SafeConsumer.ignore(dropdownItem -> {
					dropdownItem.setActive("parentPageId".equals(getOrderByCol()));
					dropdownItem.setHref(getCurrentSortingURL(), "orderByCol", "parentPageId");
					dropdownItem.setLabel(LanguageUtil.get(request, "parentPageId"));
				}));
			}
		};
	}
	
	/**
	 * Returns the current sorting URL.
	 * 
	 * @return current sorting portlet URL
	 * 
	 * @throws PortletException
	 */
	private PortletURL getCurrentSortingURL() throws PortletException {

		PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);
		sortingURL.setParameter("mvcRenderCommandName", MVCCommandNames.VIEW_PAGES);

		// Reset current page.
		sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");
		String keywords = ParamUtil.getString(request, "keywords");
		if (Validator.isNotNull(keywords)) {
			sortingURL.setParameter("keywords", keywords);
		}

		return sortingURL;
	}
	
	/**
	 * Returns sorting URL.
	 * 
	 * @return sorting URL
	 * @throws PortletException
	 */
	public PortletURL getSortingURL() throws PortletException {
		
		PortletURL sortingURL = getCurrentSortingURL();
		sortingURL.setParameter("orderByType", Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");
		return sortingURL;
	}
	
	/**
	 * Returns the page list display style. 
	 * 
	 * Current selection is stored in portal preferences.
	 * 
	 * @return current display style
	 */
	public String getDisplayStyle() {
		return "list";
	}
}

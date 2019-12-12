/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package es.aragon.base.aragon_most_visited_pages.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.aragon_most_visited_pages.exception.NoSuchMostVisitedPageException;
import es.aragon.base.aragon_most_visited_pages.model.MostVisitedPage;
import es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageImpl;
import es.aragon.base.aragon_most_visited_pages.model.impl.MostVisitedPageModelImpl;
import es.aragon.base.aragon_most_visited_pages.service.persistence.MostVisitedPagePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the most visited page service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MostVisitedPagePersistence
 * @see es.aragon.base.aragon_most_visited_pages.service.persistence.MostVisitedPageUtil
 * @generated
 */
@ProviderType
public class MostVisitedPagePersistenceImpl extends BasePersistenceImpl<MostVisitedPage>
	implements MostVisitedPagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MostVisitedPageUtil} to access the most visited page persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MostVisitedPageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageModelImpl.FINDER_CACHE_ENABLED,
			MostVisitedPageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageModelImpl.FINDER_CACHE_ENABLED,
			MostVisitedPageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageModelImpl.FINDER_CACHE_ENABLED,
			MostVisitedPageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageModelImpl.FINDER_CACHE_ENABLED,
			MostVisitedPageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MostVisitedPageModelImpl.UUID_COLUMN_BITMASK |
			MostVisitedPageModelImpl.VISITS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the most visited pages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching most visited pages
	 */
	@Override
	public List<MostVisitedPage> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the most visited pages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of most visited pages
	 * @param end the upper bound of the range of most visited pages (not inclusive)
	 * @return the range of matching most visited pages
	 */
	@Override
	public List<MostVisitedPage> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the most visited pages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of most visited pages
	 * @param end the upper bound of the range of most visited pages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching most visited pages
	 */
	@Override
	public List<MostVisitedPage> findByUuid(String uuid, int start, int end,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the most visited pages where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of most visited pages
	 * @param end the upper bound of the range of most visited pages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching most visited pages
	 */
	@Override
	public List<MostVisitedPage> findByUuid(String uuid, int start, int end,
		OrderByComparator<MostVisitedPage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<MostVisitedPage> list = null;

		if (retrieveFromCache) {
			list = (List<MostVisitedPage>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MostVisitedPage mostVisitedPage : list) {
					if (!Objects.equals(uuid, mostVisitedPage.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MOSTVISITEDPAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MostVisitedPageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<MostVisitedPage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MostVisitedPage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first most visited page in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching most visited page
	 * @throws NoSuchMostVisitedPageException if a matching most visited page could not be found
	 */
	@Override
	public MostVisitedPage findByUuid_First(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator)
		throws NoSuchMostVisitedPageException {
		MostVisitedPage mostVisitedPage = fetchByUuid_First(uuid,
				orderByComparator);

		if (mostVisitedPage != null) {
			return mostVisitedPage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchMostVisitedPageException(msg.toString());
	}

	/**
	 * Returns the first most visited page in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching most visited page, or <code>null</code> if a matching most visited page could not be found
	 */
	@Override
	public MostVisitedPage fetchByUuid_First(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		List<MostVisitedPage> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last most visited page in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching most visited page
	 * @throws NoSuchMostVisitedPageException if a matching most visited page could not be found
	 */
	@Override
	public MostVisitedPage findByUuid_Last(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator)
		throws NoSuchMostVisitedPageException {
		MostVisitedPage mostVisitedPage = fetchByUuid_Last(uuid,
				orderByComparator);

		if (mostVisitedPage != null) {
			return mostVisitedPage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchMostVisitedPageException(msg.toString());
	}

	/**
	 * Returns the last most visited page in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching most visited page, or <code>null</code> if a matching most visited page could not be found
	 */
	@Override
	public MostVisitedPage fetchByUuid_Last(String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MostVisitedPage> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the most visited pages before and after the current most visited page in the ordered set where uuid = &#63;.
	 *
	 * @param mostVisitedPageId the primary key of the current most visited page
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next most visited page
	 * @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	 */
	@Override
	public MostVisitedPage[] findByUuid_PrevAndNext(long mostVisitedPageId,
		String uuid, OrderByComparator<MostVisitedPage> orderByComparator)
		throws NoSuchMostVisitedPageException {
		MostVisitedPage mostVisitedPage = findByPrimaryKey(mostVisitedPageId);

		Session session = null;

		try {
			session = openSession();

			MostVisitedPage[] array = new MostVisitedPageImpl[3];

			array[0] = getByUuid_PrevAndNext(session, mostVisitedPage, uuid,
					orderByComparator, true);

			array[1] = mostVisitedPage;

			array[2] = getByUuid_PrevAndNext(session, mostVisitedPage, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MostVisitedPage getByUuid_PrevAndNext(Session session,
		MostVisitedPage mostVisitedPage, String uuid,
		OrderByComparator<MostVisitedPage> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MOSTVISITEDPAGE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MostVisitedPageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mostVisitedPage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MostVisitedPage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the most visited pages where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MostVisitedPage mostVisitedPage : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(mostVisitedPage);
		}
	}

	/**
	 * Returns the number of most visited pages where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching most visited pages
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MOSTVISITEDPAGE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "mostVisitedPage.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "mostVisitedPage.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(mostVisitedPage.uuid IS NULL OR mostVisitedPage.uuid = '')";

	public MostVisitedPagePersistenceImpl() {
		setModelClass(MostVisitedPage.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("path", "path_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the most visited page in the entity cache if it is enabled.
	 *
	 * @param mostVisitedPage the most visited page
	 */
	@Override
	public void cacheResult(MostVisitedPage mostVisitedPage) {
		entityCache.putResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageImpl.class, mostVisitedPage.getPrimaryKey(),
			mostVisitedPage);

		mostVisitedPage.resetOriginalValues();
	}

	/**
	 * Caches the most visited pages in the entity cache if it is enabled.
	 *
	 * @param mostVisitedPages the most visited pages
	 */
	@Override
	public void cacheResult(List<MostVisitedPage> mostVisitedPages) {
		for (MostVisitedPage mostVisitedPage : mostVisitedPages) {
			if (entityCache.getResult(
						MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
						MostVisitedPageImpl.class,
						mostVisitedPage.getPrimaryKey()) == null) {
				cacheResult(mostVisitedPage);
			}
			else {
				mostVisitedPage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all most visited pages.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MostVisitedPageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the most visited page.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MostVisitedPage mostVisitedPage) {
		entityCache.removeResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageImpl.class, mostVisitedPage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MostVisitedPage> mostVisitedPages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MostVisitedPage mostVisitedPage : mostVisitedPages) {
			entityCache.removeResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
				MostVisitedPageImpl.class, mostVisitedPage.getPrimaryKey());
		}
	}

	/**
	 * Creates a new most visited page with the primary key. Does not add the most visited page to the database.
	 *
	 * @param mostVisitedPageId the primary key for the new most visited page
	 * @return the new most visited page
	 */
	@Override
	public MostVisitedPage create(long mostVisitedPageId) {
		MostVisitedPage mostVisitedPage = new MostVisitedPageImpl();

		mostVisitedPage.setNew(true);
		mostVisitedPage.setPrimaryKey(mostVisitedPageId);

		String uuid = PortalUUIDUtil.generate();

		mostVisitedPage.setUuid(uuid);

		return mostVisitedPage;
	}

	/**
	 * Removes the most visited page with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mostVisitedPageId the primary key of the most visited page
	 * @return the most visited page that was removed
	 * @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	 */
	@Override
	public MostVisitedPage remove(long mostVisitedPageId)
		throws NoSuchMostVisitedPageException {
		return remove((Serializable)mostVisitedPageId);
	}

	/**
	 * Removes the most visited page with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the most visited page
	 * @return the most visited page that was removed
	 * @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	 */
	@Override
	public MostVisitedPage remove(Serializable primaryKey)
		throws NoSuchMostVisitedPageException {
		Session session = null;

		try {
			session = openSession();

			MostVisitedPage mostVisitedPage = (MostVisitedPage)session.get(MostVisitedPageImpl.class,
					primaryKey);

			if (mostVisitedPage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMostVisitedPageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mostVisitedPage);
		}
		catch (NoSuchMostVisitedPageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MostVisitedPage removeImpl(MostVisitedPage mostVisitedPage) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mostVisitedPage)) {
				mostVisitedPage = (MostVisitedPage)session.get(MostVisitedPageImpl.class,
						mostVisitedPage.getPrimaryKeyObj());
			}

			if (mostVisitedPage != null) {
				session.delete(mostVisitedPage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mostVisitedPage != null) {
			clearCache(mostVisitedPage);
		}

		return mostVisitedPage;
	}

	@Override
	public MostVisitedPage updateImpl(MostVisitedPage mostVisitedPage) {
		boolean isNew = mostVisitedPage.isNew();

		if (!(mostVisitedPage instanceof MostVisitedPageModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(mostVisitedPage.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(mostVisitedPage);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in mostVisitedPage proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MostVisitedPage implementation " +
				mostVisitedPage.getClass());
		}

		MostVisitedPageModelImpl mostVisitedPageModelImpl = (MostVisitedPageModelImpl)mostVisitedPage;

		if (Validator.isNull(mostVisitedPage.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			mostVisitedPage.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (mostVisitedPage.isNew()) {
				session.save(mostVisitedPage);

				mostVisitedPage.setNew(false);
			}
			else {
				mostVisitedPage = (MostVisitedPage)session.merge(mostVisitedPage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!MostVisitedPageModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { mostVisitedPageModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((mostVisitedPageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mostVisitedPageModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { mostVisitedPageModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
			MostVisitedPageImpl.class, mostVisitedPage.getPrimaryKey(),
			mostVisitedPage, false);

		mostVisitedPage.resetOriginalValues();

		return mostVisitedPage;
	}

	/**
	 * Returns the most visited page with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the most visited page
	 * @return the most visited page
	 * @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	 */
	@Override
	public MostVisitedPage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMostVisitedPageException {
		MostVisitedPage mostVisitedPage = fetchByPrimaryKey(primaryKey);

		if (mostVisitedPage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMostVisitedPageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mostVisitedPage;
	}

	/**
	 * Returns the most visited page with the primary key or throws a {@link NoSuchMostVisitedPageException} if it could not be found.
	 *
	 * @param mostVisitedPageId the primary key of the most visited page
	 * @return the most visited page
	 * @throws NoSuchMostVisitedPageException if a most visited page with the primary key could not be found
	 */
	@Override
	public MostVisitedPage findByPrimaryKey(long mostVisitedPageId)
		throws NoSuchMostVisitedPageException {
		return findByPrimaryKey((Serializable)mostVisitedPageId);
	}

	/**
	 * Returns the most visited page with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the most visited page
	 * @return the most visited page, or <code>null</code> if a most visited page with the primary key could not be found
	 */
	@Override
	public MostVisitedPage fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
				MostVisitedPageImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MostVisitedPage mostVisitedPage = (MostVisitedPage)serializable;

		if (mostVisitedPage == null) {
			Session session = null;

			try {
				session = openSession();

				mostVisitedPage = (MostVisitedPage)session.get(MostVisitedPageImpl.class,
						primaryKey);

				if (mostVisitedPage != null) {
					cacheResult(mostVisitedPage);
				}
				else {
					entityCache.putResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
						MostVisitedPageImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
					MostVisitedPageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mostVisitedPage;
	}

	/**
	 * Returns the most visited page with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mostVisitedPageId the primary key of the most visited page
	 * @return the most visited page, or <code>null</code> if a most visited page with the primary key could not be found
	 */
	@Override
	public MostVisitedPage fetchByPrimaryKey(long mostVisitedPageId) {
		return fetchByPrimaryKey((Serializable)mostVisitedPageId);
	}

	@Override
	public Map<Serializable, MostVisitedPage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MostVisitedPage> map = new HashMap<Serializable, MostVisitedPage>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MostVisitedPage mostVisitedPage = fetchByPrimaryKey(primaryKey);

			if (mostVisitedPage != null) {
				map.put(primaryKey, mostVisitedPage);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
					MostVisitedPageImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MostVisitedPage)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MOSTVISITEDPAGE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (MostVisitedPage mostVisitedPage : (List<MostVisitedPage>)q.list()) {
				map.put(mostVisitedPage.getPrimaryKeyObj(), mostVisitedPage);

				cacheResult(mostVisitedPage);

				uncachedPrimaryKeys.remove(mostVisitedPage.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MostVisitedPageModelImpl.ENTITY_CACHE_ENABLED,
					MostVisitedPageImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the most visited pages.
	 *
	 * @return the most visited pages
	 */
	@Override
	public List<MostVisitedPage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the most visited pages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of most visited pages
	 * @param end the upper bound of the range of most visited pages (not inclusive)
	 * @return the range of most visited pages
	 */
	@Override
	public List<MostVisitedPage> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the most visited pages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of most visited pages
	 * @param end the upper bound of the range of most visited pages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of most visited pages
	 */
	@Override
	public List<MostVisitedPage> findAll(int start, int end,
		OrderByComparator<MostVisitedPage> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the most visited pages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MostVisitedPageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of most visited pages
	 * @param end the upper bound of the range of most visited pages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of most visited pages
	 */
	@Override
	public List<MostVisitedPage> findAll(int start, int end,
		OrderByComparator<MostVisitedPage> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MostVisitedPage> list = null;

		if (retrieveFromCache) {
			list = (List<MostVisitedPage>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MOSTVISITEDPAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MOSTVISITEDPAGE;

				if (pagination) {
					sql = sql.concat(MostVisitedPageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MostVisitedPage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MostVisitedPage>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the most visited pages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MostVisitedPage mostVisitedPage : findAll()) {
			remove(mostVisitedPage);
		}
	}

	/**
	 * Returns the number of most visited pages.
	 *
	 * @return the number of most visited pages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MOSTVISITEDPAGE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MostVisitedPageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the most visited page persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MostVisitedPageImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MOSTVISITEDPAGE = "SELECT mostVisitedPage FROM MostVisitedPage mostVisitedPage";
	private static final String _SQL_SELECT_MOSTVISITEDPAGE_WHERE_PKS_IN = "SELECT mostVisitedPage FROM MostVisitedPage mostVisitedPage WHERE mostVisitedPageId IN (";
	private static final String _SQL_SELECT_MOSTVISITEDPAGE_WHERE = "SELECT mostVisitedPage FROM MostVisitedPage mostVisitedPage WHERE ";
	private static final String _SQL_COUNT_MOSTVISITEDPAGE = "SELECT COUNT(mostVisitedPage) FROM MostVisitedPage mostVisitedPage";
	private static final String _SQL_COUNT_MOSTVISITEDPAGE_WHERE = "SELECT COUNT(mostVisitedPage) FROM MostVisitedPage mostVisitedPage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mostVisitedPage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MostVisitedPage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MostVisitedPage exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MostVisitedPagePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "path"
			});
}
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

package es.aragon.base.migration.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.migration.exception.NoSuchContentUrlException;
import es.aragon.base.migration.model.ContentUrl;
import es.aragon.base.migration.model.impl.ContentUrlImpl;
import es.aragon.base.migration.model.impl.ContentUrlModelImpl;
import es.aragon.base.migration.service.persistence.ContentUrlPersistence;

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
 * The persistence implementation for the content url service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentUrlPersistence
 * @see es.aragon.base.migration.service.persistence.ContentUrlUtil
 * @generated
 */
@ProviderType
public class ContentUrlPersistenceImpl extends BasePersistenceImpl<ContentUrl>
	implements ContentUrlPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContentUrlUtil} to access the content url persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContentUrlImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, ContentUrlImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, ContentUrlImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, ContentUrlImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, ContentUrlImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ContentUrlModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the content urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching content urls
	 */
	@Override
	public List<ContentUrl> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content urls
	 * @param end the upper bound of the range of content urls (not inclusive)
	 * @return the range of matching content urls
	 */
	@Override
	public List<ContentUrl> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the content urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content urls
	 * @param end the upper bound of the range of content urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content urls
	 */
	@Override
	public List<ContentUrl> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentUrl> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content urls
	 * @param end the upper bound of the range of content urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching content urls
	 */
	@Override
	public List<ContentUrl> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentUrl> orderByComparator,
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

		List<ContentUrl> list = null;

		if (retrieveFromCache) {
			list = (List<ContentUrl>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContentUrl contentUrl : list) {
					if (!Objects.equals(uuid, contentUrl.getUuid())) {
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

			query.append(_SQL_SELECT_CONTENTURL_WHERE);

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
				query.append(ContentUrlModelImpl.ORDER_BY_JPQL);
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
					list = (List<ContentUrl>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentUrl>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first content url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content url
	 * @throws NoSuchContentUrlException if a matching content url could not be found
	 */
	@Override
	public ContentUrl findByUuid_First(String uuid,
		OrderByComparator<ContentUrl> orderByComparator)
		throws NoSuchContentUrlException {
		ContentUrl contentUrl = fetchByUuid_First(uuid, orderByComparator);

		if (contentUrl != null) {
			return contentUrl;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentUrlException(msg.toString());
	}

	/**
	 * Returns the first content url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content url, or <code>null</code> if a matching content url could not be found
	 */
	@Override
	public ContentUrl fetchByUuid_First(String uuid,
		OrderByComparator<ContentUrl> orderByComparator) {
		List<ContentUrl> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content url
	 * @throws NoSuchContentUrlException if a matching content url could not be found
	 */
	@Override
	public ContentUrl findByUuid_Last(String uuid,
		OrderByComparator<ContentUrl> orderByComparator)
		throws NoSuchContentUrlException {
		ContentUrl contentUrl = fetchByUuid_Last(uuid, orderByComparator);

		if (contentUrl != null) {
			return contentUrl;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentUrlException(msg.toString());
	}

	/**
	 * Returns the last content url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content url, or <code>null</code> if a matching content url could not be found
	 */
	@Override
	public ContentUrl fetchByUuid_Last(String uuid,
		OrderByComparator<ContentUrl> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ContentUrl> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the content urls before and after the current content url in the ordered set where uuid = &#63;.
	 *
	 * @param contentUrlId the primary key of the current content url
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content url
	 * @throws NoSuchContentUrlException if a content url with the primary key could not be found
	 */
	@Override
	public ContentUrl[] findByUuid_PrevAndNext(long contentUrlId, String uuid,
		OrderByComparator<ContentUrl> orderByComparator)
		throws NoSuchContentUrlException {
		ContentUrl contentUrl = findByPrimaryKey(contentUrlId);

		Session session = null;

		try {
			session = openSession();

			ContentUrl[] array = new ContentUrlImpl[3];

			array[0] = getByUuid_PrevAndNext(session, contentUrl, uuid,
					orderByComparator, true);

			array[1] = contentUrl;

			array[2] = getByUuid_PrevAndNext(session, contentUrl, uuid,
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

	protected ContentUrl getByUuid_PrevAndNext(Session session,
		ContentUrl contentUrl, String uuid,
		OrderByComparator<ContentUrl> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENTURL_WHERE);

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
			query.append(ContentUrlModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(contentUrl);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContentUrl> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the content urls where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ContentUrl contentUrl : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(contentUrl);
		}
	}

	/**
	 * Returns the number of content urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching content urls
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTURL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "contentUrl.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "contentUrl.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(contentUrl.uuid IS NULL OR contentUrl.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CONTENTORIGINID = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, ContentUrlImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByContentOriginId",
			new String[] { Long.class.getName() },
			ContentUrlModelImpl.CONTENTORIGINID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTORIGINID = new FinderPath(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByContentOriginId", new String[] { Long.class.getName() });

	/**
	 * Returns the content url where contentOriginId = &#63; or throws a {@link NoSuchContentUrlException} if it could not be found.
	 *
	 * @param contentOriginId the content origin ID
	 * @return the matching content url
	 * @throws NoSuchContentUrlException if a matching content url could not be found
	 */
	@Override
	public ContentUrl findByContentOriginId(long contentOriginId)
		throws NoSuchContentUrlException {
		ContentUrl contentUrl = fetchByContentOriginId(contentOriginId);

		if (contentUrl == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contentOriginId=");
			msg.append(contentOriginId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchContentUrlException(msg.toString());
		}

		return contentUrl;
	}

	/**
	 * Returns the content url where contentOriginId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param contentOriginId the content origin ID
	 * @return the matching content url, or <code>null</code> if a matching content url could not be found
	 */
	@Override
	public ContentUrl fetchByContentOriginId(long contentOriginId) {
		return fetchByContentOriginId(contentOriginId, true);
	}

	/**
	 * Returns the content url where contentOriginId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param contentOriginId the content origin ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching content url, or <code>null</code> if a matching content url could not be found
	 */
	@Override
	public ContentUrl fetchByContentOriginId(long contentOriginId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { contentOriginId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CONTENTORIGINID,
					finderArgs, this);
		}

		if (result instanceof ContentUrl) {
			ContentUrl contentUrl = (ContentUrl)result;

			if ((contentOriginId != contentUrl.getContentOriginId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CONTENTURL_WHERE);

			query.append(_FINDER_COLUMN_CONTENTORIGINID_CONTENTORIGINID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentOriginId);

				List<ContentUrl> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CONTENTORIGINID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ContentUrlPersistenceImpl.fetchByContentOriginId(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ContentUrl contentUrl = list.get(0);

					result = contentUrl;

					cacheResult(contentUrl);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CONTENTORIGINID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ContentUrl)result;
		}
	}

	/**
	 * Removes the content url where contentOriginId = &#63; from the database.
	 *
	 * @param contentOriginId the content origin ID
	 * @return the content url that was removed
	 */
	@Override
	public ContentUrl removeByContentOriginId(long contentOriginId)
		throws NoSuchContentUrlException {
		ContentUrl contentUrl = findByContentOriginId(contentOriginId);

		return remove(contentUrl);
	}

	/**
	 * Returns the number of content urls where contentOriginId = &#63;.
	 *
	 * @param contentOriginId the content origin ID
	 * @return the number of matching content urls
	 */
	@Override
	public int countByContentOriginId(long contentOriginId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTORIGINID;

		Object[] finderArgs = new Object[] { contentOriginId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTURL_WHERE);

			query.append(_FINDER_COLUMN_CONTENTORIGINID_CONTENTORIGINID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentOriginId);

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

	private static final String _FINDER_COLUMN_CONTENTORIGINID_CONTENTORIGINID_2 =
		"contentUrl.contentOriginId = ?";

	public ContentUrlPersistenceImpl() {
		setModelClass(ContentUrl.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the content url in the entity cache if it is enabled.
	 *
	 * @param contentUrl the content url
	 */
	@Override
	public void cacheResult(ContentUrl contentUrl) {
		entityCache.putResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlImpl.class, contentUrl.getPrimaryKey(), contentUrl);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CONTENTORIGINID,
			new Object[] { contentUrl.getContentOriginId() }, contentUrl);

		contentUrl.resetOriginalValues();
	}

	/**
	 * Caches the content urls in the entity cache if it is enabled.
	 *
	 * @param contentUrls the content urls
	 */
	@Override
	public void cacheResult(List<ContentUrl> contentUrls) {
		for (ContentUrl contentUrl : contentUrls) {
			if (entityCache.getResult(
						ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
						ContentUrlImpl.class, contentUrl.getPrimaryKey()) == null) {
				cacheResult(contentUrl);
			}
			else {
				contentUrl.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all content urls.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContentUrlImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the content url.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContentUrl contentUrl) {
		entityCache.removeResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlImpl.class, contentUrl.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ContentUrlModelImpl)contentUrl, true);
	}

	@Override
	public void clearCache(List<ContentUrl> contentUrls) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContentUrl contentUrl : contentUrls) {
			entityCache.removeResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
				ContentUrlImpl.class, contentUrl.getPrimaryKey());

			clearUniqueFindersCache((ContentUrlModelImpl)contentUrl, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ContentUrlModelImpl contentUrlModelImpl) {
		Object[] args = new Object[] { contentUrlModelImpl.getContentOriginId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CONTENTORIGINID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CONTENTORIGINID, args,
			contentUrlModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ContentUrlModelImpl contentUrlModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					contentUrlModelImpl.getContentOriginId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTORIGINID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CONTENTORIGINID, args);
		}

		if ((contentUrlModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CONTENTORIGINID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					contentUrlModelImpl.getOriginalContentOriginId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTORIGINID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CONTENTORIGINID, args);
		}
	}

	/**
	 * Creates a new content url with the primary key. Does not add the content url to the database.
	 *
	 * @param contentUrlId the primary key for the new content url
	 * @return the new content url
	 */
	@Override
	public ContentUrl create(long contentUrlId) {
		ContentUrl contentUrl = new ContentUrlImpl();

		contentUrl.setNew(true);
		contentUrl.setPrimaryKey(contentUrlId);

		String uuid = PortalUUIDUtil.generate();

		contentUrl.setUuid(uuid);

		return contentUrl;
	}

	/**
	 * Removes the content url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentUrlId the primary key of the content url
	 * @return the content url that was removed
	 * @throws NoSuchContentUrlException if a content url with the primary key could not be found
	 */
	@Override
	public ContentUrl remove(long contentUrlId)
		throws NoSuchContentUrlException {
		return remove((Serializable)contentUrlId);
	}

	/**
	 * Removes the content url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the content url
	 * @return the content url that was removed
	 * @throws NoSuchContentUrlException if a content url with the primary key could not be found
	 */
	@Override
	public ContentUrl remove(Serializable primaryKey)
		throws NoSuchContentUrlException {
		Session session = null;

		try {
			session = openSession();

			ContentUrl contentUrl = (ContentUrl)session.get(ContentUrlImpl.class,
					primaryKey);

			if (contentUrl == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentUrlException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contentUrl);
		}
		catch (NoSuchContentUrlException nsee) {
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
	protected ContentUrl removeImpl(ContentUrl contentUrl) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contentUrl)) {
				contentUrl = (ContentUrl)session.get(ContentUrlImpl.class,
						contentUrl.getPrimaryKeyObj());
			}

			if (contentUrl != null) {
				session.delete(contentUrl);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contentUrl != null) {
			clearCache(contentUrl);
		}

		return contentUrl;
	}

	@Override
	public ContentUrl updateImpl(ContentUrl contentUrl) {
		boolean isNew = contentUrl.isNew();

		if (!(contentUrl instanceof ContentUrlModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(contentUrl.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(contentUrl);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in contentUrl proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ContentUrl implementation " +
				contentUrl.getClass());
		}

		ContentUrlModelImpl contentUrlModelImpl = (ContentUrlModelImpl)contentUrl;

		if (Validator.isNull(contentUrl.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			contentUrl.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (contentUrl.isNew()) {
				session.save(contentUrl);

				contentUrl.setNew(false);
			}
			else {
				contentUrl = (ContentUrl)session.merge(contentUrl);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContentUrlModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { contentUrlModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contentUrlModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentUrlModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { contentUrlModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
			ContentUrlImpl.class, contentUrl.getPrimaryKey(), contentUrl, false);

		clearUniqueFindersCache(contentUrlModelImpl, false);
		cacheUniqueFindersCache(contentUrlModelImpl);

		contentUrl.resetOriginalValues();

		return contentUrl;
	}

	/**
	 * Returns the content url with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the content url
	 * @return the content url
	 * @throws NoSuchContentUrlException if a content url with the primary key could not be found
	 */
	@Override
	public ContentUrl findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentUrlException {
		ContentUrl contentUrl = fetchByPrimaryKey(primaryKey);

		if (contentUrl == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentUrlException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contentUrl;
	}

	/**
	 * Returns the content url with the primary key or throws a {@link NoSuchContentUrlException} if it could not be found.
	 *
	 * @param contentUrlId the primary key of the content url
	 * @return the content url
	 * @throws NoSuchContentUrlException if a content url with the primary key could not be found
	 */
	@Override
	public ContentUrl findByPrimaryKey(long contentUrlId)
		throws NoSuchContentUrlException {
		return findByPrimaryKey((Serializable)contentUrlId);
	}

	/**
	 * Returns the content url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the content url
	 * @return the content url, or <code>null</code> if a content url with the primary key could not be found
	 */
	@Override
	public ContentUrl fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
				ContentUrlImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContentUrl contentUrl = (ContentUrl)serializable;

		if (contentUrl == null) {
			Session session = null;

			try {
				session = openSession();

				contentUrl = (ContentUrl)session.get(ContentUrlImpl.class,
						primaryKey);

				if (contentUrl != null) {
					cacheResult(contentUrl);
				}
				else {
					entityCache.putResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
						ContentUrlImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
					ContentUrlImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contentUrl;
	}

	/**
	 * Returns the content url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentUrlId the primary key of the content url
	 * @return the content url, or <code>null</code> if a content url with the primary key could not be found
	 */
	@Override
	public ContentUrl fetchByPrimaryKey(long contentUrlId) {
		return fetchByPrimaryKey((Serializable)contentUrlId);
	}

	@Override
	public Map<Serializable, ContentUrl> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContentUrl> map = new HashMap<Serializable, ContentUrl>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContentUrl contentUrl = fetchByPrimaryKey(primaryKey);

			if (contentUrl != null) {
				map.put(primaryKey, contentUrl);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
					ContentUrlImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContentUrl)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTENTURL_WHERE_PKS_IN);

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

			for (ContentUrl contentUrl : (List<ContentUrl>)q.list()) {
				map.put(contentUrl.getPrimaryKeyObj(), contentUrl);

				cacheResult(contentUrl);

				uncachedPrimaryKeys.remove(contentUrl.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContentUrlModelImpl.ENTITY_CACHE_ENABLED,
					ContentUrlImpl.class, primaryKey, nullModel);
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
	 * Returns all the content urls.
	 *
	 * @return the content urls
	 */
	@Override
	public List<ContentUrl> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content urls
	 * @param end the upper bound of the range of content urls (not inclusive)
	 * @return the range of content urls
	 */
	@Override
	public List<ContentUrl> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the content urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content urls
	 * @param end the upper bound of the range of content urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of content urls
	 */
	@Override
	public List<ContentUrl> findAll(int start, int end,
		OrderByComparator<ContentUrl> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentUrlModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content urls
	 * @param end the upper bound of the range of content urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of content urls
	 */
	@Override
	public List<ContentUrl> findAll(int start, int end,
		OrderByComparator<ContentUrl> orderByComparator,
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

		List<ContentUrl> list = null;

		if (retrieveFromCache) {
			list = (List<ContentUrl>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTENTURL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENTURL;

				if (pagination) {
					sql = sql.concat(ContentUrlModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContentUrl>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentUrl>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the content urls from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContentUrl contentUrl : findAll()) {
			remove(contentUrl);
		}
	}

	/**
	 * Returns the number of content urls.
	 *
	 * @return the number of content urls
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTENTURL);

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
		return ContentUrlModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the content url persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContentUrlImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONTENTURL = "SELECT contentUrl FROM ContentUrl contentUrl";
	private static final String _SQL_SELECT_CONTENTURL_WHERE_PKS_IN = "SELECT contentUrl FROM ContentUrl contentUrl WHERE contentUrlId IN (";
	private static final String _SQL_SELECT_CONTENTURL_WHERE = "SELECT contentUrl FROM ContentUrl contentUrl WHERE ";
	private static final String _SQL_COUNT_CONTENTURL = "SELECT COUNT(contentUrl) FROM ContentUrl contentUrl";
	private static final String _SQL_COUNT_CONTENTURL_WHERE = "SELECT COUNT(contentUrl) FROM ContentUrl contentUrl WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contentUrl.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContentUrl exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContentUrl exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContentUrlPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}
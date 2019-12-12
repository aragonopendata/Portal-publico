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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.migration.exception.NoSuchContentRelatedException;
import es.aragon.base.migration.model.ContentRelated;
import es.aragon.base.migration.model.impl.ContentRelatedImpl;
import es.aragon.base.migration.model.impl.ContentRelatedModelImpl;
import es.aragon.base.migration.service.persistence.ContentRelatedPersistence;

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
 * The persistence implementation for the content related service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentRelatedPersistence
 * @see es.aragon.base.migration.service.persistence.ContentRelatedUtil
 * @generated
 */
@ProviderType
public class ContentRelatedPersistenceImpl extends BasePersistenceImpl<ContentRelated>
	implements ContentRelatedPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContentRelatedUtil} to access the content related persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContentRelatedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED,
			ContentRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED,
			ContentRelatedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED,
			ContentRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED,
			ContentRelatedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ContentRelatedModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the content relateds where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching content relateds
	 */
	@Override
	public List<ContentRelated> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content relateds where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @return the range of matching content relateds
	 */
	@Override
	public List<ContentRelated> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the content relateds where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content relateds
	 */
	@Override
	public List<ContentRelated> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentRelated> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content relateds where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching content relateds
	 */
	@Override
	public List<ContentRelated> findByUuid(String uuid, int start, int end,
		OrderByComparator<ContentRelated> orderByComparator,
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

		List<ContentRelated> list = null;

		if (retrieveFromCache) {
			list = (List<ContentRelated>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContentRelated contentRelated : list) {
					if (!Objects.equals(uuid, contentRelated.getUuid())) {
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

			query.append(_SQL_SELECT_CONTENTRELATED_WHERE);

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
				query.append(ContentRelatedModelImpl.ORDER_BY_JPQL);
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
					list = (List<ContentRelated>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentRelated>)QueryUtil.list(q,
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
	 * Returns the first content related in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content related
	 * @throws NoSuchContentRelatedException if a matching content related could not be found
	 */
	@Override
	public ContentRelated findByUuid_First(String uuid,
		OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException {
		ContentRelated contentRelated = fetchByUuid_First(uuid,
				orderByComparator);

		if (contentRelated != null) {
			return contentRelated;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentRelatedException(msg.toString());
	}

	/**
	 * Returns the first content related in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content related, or <code>null</code> if a matching content related could not be found
	 */
	@Override
	public ContentRelated fetchByUuid_First(String uuid,
		OrderByComparator<ContentRelated> orderByComparator) {
		List<ContentRelated> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content related in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content related
	 * @throws NoSuchContentRelatedException if a matching content related could not be found
	 */
	@Override
	public ContentRelated findByUuid_Last(String uuid,
		OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException {
		ContentRelated contentRelated = fetchByUuid_Last(uuid, orderByComparator);

		if (contentRelated != null) {
			return contentRelated;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentRelatedException(msg.toString());
	}

	/**
	 * Returns the last content related in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content related, or <code>null</code> if a matching content related could not be found
	 */
	@Override
	public ContentRelated fetchByUuid_Last(String uuid,
		OrderByComparator<ContentRelated> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ContentRelated> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the content relateds before and after the current content related in the ordered set where uuid = &#63;.
	 *
	 * @param contentRelatedId the primary key of the current content related
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content related
	 * @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated[] findByUuid_PrevAndNext(long contentRelatedId,
		String uuid, OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException {
		ContentRelated contentRelated = findByPrimaryKey(contentRelatedId);

		Session session = null;

		try {
			session = openSession();

			ContentRelated[] array = new ContentRelatedImpl[3];

			array[0] = getByUuid_PrevAndNext(session, contentRelated, uuid,
					orderByComparator, true);

			array[1] = contentRelated;

			array[2] = getByUuid_PrevAndNext(session, contentRelated, uuid,
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

	protected ContentRelated getByUuid_PrevAndNext(Session session,
		ContentRelated contentRelated, String uuid,
		OrderByComparator<ContentRelated> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENTRELATED_WHERE);

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
			query.append(ContentRelatedModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(contentRelated);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContentRelated> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the content relateds where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ContentRelated contentRelated : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contentRelated);
		}
	}

	/**
	 * Returns the number of content relateds where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching content relateds
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTRELATED_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "contentRelated.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "contentRelated.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(contentRelated.uuid IS NULL OR contentRelated.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTPARENTID =
		new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED,
			ContentRelatedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByContentParentId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPARENTID =
		new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED,
			ContentRelatedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContentParentId",
			new String[] { Long.class.getName() },
			ContentRelatedModelImpl.CONTENTPARENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTPARENTID = new FinderPath(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByContentParentId", new String[] { Long.class.getName() });

	/**
	 * Returns all the content relateds where contentParentId = &#63;.
	 *
	 * @param contentParentId the content parent ID
	 * @return the matching content relateds
	 */
	@Override
	public List<ContentRelated> findByContentParentId(long contentParentId) {
		return findByContentParentId(contentParentId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content relateds where contentParentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentParentId the content parent ID
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @return the range of matching content relateds
	 */
	@Override
	public List<ContentRelated> findByContentParentId(long contentParentId,
		int start, int end) {
		return findByContentParentId(contentParentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the content relateds where contentParentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentParentId the content parent ID
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching content relateds
	 */
	@Override
	public List<ContentRelated> findByContentParentId(long contentParentId,
		int start, int end, OrderByComparator<ContentRelated> orderByComparator) {
		return findByContentParentId(contentParentId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content relateds where contentParentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentParentId the content parent ID
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching content relateds
	 */
	@Override
	public List<ContentRelated> findByContentParentId(long contentParentId,
		int start, int end,
		OrderByComparator<ContentRelated> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPARENTID;
			finderArgs = new Object[] { contentParentId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTPARENTID;
			finderArgs = new Object[] {
					contentParentId,
					
					start, end, orderByComparator
				};
		}

		List<ContentRelated> list = null;

		if (retrieveFromCache) {
			list = (List<ContentRelated>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContentRelated contentRelated : list) {
					if ((contentParentId != contentRelated.getContentParentId())) {
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

			query.append(_SQL_SELECT_CONTENTRELATED_WHERE);

			query.append(_FINDER_COLUMN_CONTENTPARENTID_CONTENTPARENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentRelatedModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentParentId);

				if (!pagination) {
					list = (List<ContentRelated>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentRelated>)QueryUtil.list(q,
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
	 * Returns the first content related in the ordered set where contentParentId = &#63;.
	 *
	 * @param contentParentId the content parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content related
	 * @throws NoSuchContentRelatedException if a matching content related could not be found
	 */
	@Override
	public ContentRelated findByContentParentId_First(long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException {
		ContentRelated contentRelated = fetchByContentParentId_First(contentParentId,
				orderByComparator);

		if (contentRelated != null) {
			return contentRelated;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentParentId=");
		msg.append(contentParentId);

		msg.append("}");

		throw new NoSuchContentRelatedException(msg.toString());
	}

	/**
	 * Returns the first content related in the ordered set where contentParentId = &#63;.
	 *
	 * @param contentParentId the content parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content related, or <code>null</code> if a matching content related could not be found
	 */
	@Override
	public ContentRelated fetchByContentParentId_First(long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator) {
		List<ContentRelated> list = findByContentParentId(contentParentId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content related in the ordered set where contentParentId = &#63;.
	 *
	 * @param contentParentId the content parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content related
	 * @throws NoSuchContentRelatedException if a matching content related could not be found
	 */
	@Override
	public ContentRelated findByContentParentId_Last(long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException {
		ContentRelated contentRelated = fetchByContentParentId_Last(contentParentId,
				orderByComparator);

		if (contentRelated != null) {
			return contentRelated;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentParentId=");
		msg.append(contentParentId);

		msg.append("}");

		throw new NoSuchContentRelatedException(msg.toString());
	}

	/**
	 * Returns the last content related in the ordered set where contentParentId = &#63;.
	 *
	 * @param contentParentId the content parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content related, or <code>null</code> if a matching content related could not be found
	 */
	@Override
	public ContentRelated fetchByContentParentId_Last(long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator) {
		int count = countByContentParentId(contentParentId);

		if (count == 0) {
			return null;
		}

		List<ContentRelated> list = findByContentParentId(contentParentId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the content relateds before and after the current content related in the ordered set where contentParentId = &#63;.
	 *
	 * @param contentRelatedId the primary key of the current content related
	 * @param contentParentId the content parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content related
	 * @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated[] findByContentParentId_PrevAndNext(
		long contentRelatedId, long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator)
		throws NoSuchContentRelatedException {
		ContentRelated contentRelated = findByPrimaryKey(contentRelatedId);

		Session session = null;

		try {
			session = openSession();

			ContentRelated[] array = new ContentRelatedImpl[3];

			array[0] = getByContentParentId_PrevAndNext(session,
					contentRelated, contentParentId, orderByComparator, true);

			array[1] = contentRelated;

			array[2] = getByContentParentId_PrevAndNext(session,
					contentRelated, contentParentId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContentRelated getByContentParentId_PrevAndNext(Session session,
		ContentRelated contentRelated, long contentParentId,
		OrderByComparator<ContentRelated> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENTRELATED_WHERE);

		query.append(_FINDER_COLUMN_CONTENTPARENTID_CONTENTPARENTID_2);

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
			query.append(ContentRelatedModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contentParentId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(contentRelated);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContentRelated> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the content relateds where contentParentId = &#63; from the database.
	 *
	 * @param contentParentId the content parent ID
	 */
	@Override
	public void removeByContentParentId(long contentParentId) {
		for (ContentRelated contentRelated : findByContentParentId(
				contentParentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contentRelated);
		}
	}

	/**
	 * Returns the number of content relateds where contentParentId = &#63;.
	 *
	 * @param contentParentId the content parent ID
	 * @return the number of matching content relateds
	 */
	@Override
	public int countByContentParentId(long contentParentId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTPARENTID;

		Object[] finderArgs = new Object[] { contentParentId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENTRELATED_WHERE);

			query.append(_FINDER_COLUMN_CONTENTPARENTID_CONTENTPARENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentParentId);

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

	private static final String _FINDER_COLUMN_CONTENTPARENTID_CONTENTPARENTID_2 =
		"contentRelated.contentParentId = ?";

	public ContentRelatedPersistenceImpl() {
		setModelClass(ContentRelated.class);

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
	 * Caches the content related in the entity cache if it is enabled.
	 *
	 * @param contentRelated the content related
	 */
	@Override
	public void cacheResult(ContentRelated contentRelated) {
		entityCache.putResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedImpl.class, contentRelated.getPrimaryKey(),
			contentRelated);

		contentRelated.resetOriginalValues();
	}

	/**
	 * Caches the content relateds in the entity cache if it is enabled.
	 *
	 * @param contentRelateds the content relateds
	 */
	@Override
	public void cacheResult(List<ContentRelated> contentRelateds) {
		for (ContentRelated contentRelated : contentRelateds) {
			if (entityCache.getResult(
						ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
						ContentRelatedImpl.class, contentRelated.getPrimaryKey()) == null) {
				cacheResult(contentRelated);
			}
			else {
				contentRelated.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all content relateds.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContentRelatedImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the content related.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContentRelated contentRelated) {
		entityCache.removeResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedImpl.class, contentRelated.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContentRelated> contentRelateds) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContentRelated contentRelated : contentRelateds) {
			entityCache.removeResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
				ContentRelatedImpl.class, contentRelated.getPrimaryKey());
		}
	}

	/**
	 * Creates a new content related with the primary key. Does not add the content related to the database.
	 *
	 * @param contentRelatedId the primary key for the new content related
	 * @return the new content related
	 */
	@Override
	public ContentRelated create(long contentRelatedId) {
		ContentRelated contentRelated = new ContentRelatedImpl();

		contentRelated.setNew(true);
		contentRelated.setPrimaryKey(contentRelatedId);

		String uuid = PortalUUIDUtil.generate();

		contentRelated.setUuid(uuid);

		return contentRelated;
	}

	/**
	 * Removes the content related with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentRelatedId the primary key of the content related
	 * @return the content related that was removed
	 * @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated remove(long contentRelatedId)
		throws NoSuchContentRelatedException {
		return remove((Serializable)contentRelatedId);
	}

	/**
	 * Removes the content related with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the content related
	 * @return the content related that was removed
	 * @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated remove(Serializable primaryKey)
		throws NoSuchContentRelatedException {
		Session session = null;

		try {
			session = openSession();

			ContentRelated contentRelated = (ContentRelated)session.get(ContentRelatedImpl.class,
					primaryKey);

			if (contentRelated == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contentRelated);
		}
		catch (NoSuchContentRelatedException nsee) {
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
	protected ContentRelated removeImpl(ContentRelated contentRelated) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contentRelated)) {
				contentRelated = (ContentRelated)session.get(ContentRelatedImpl.class,
						contentRelated.getPrimaryKeyObj());
			}

			if (contentRelated != null) {
				session.delete(contentRelated);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contentRelated != null) {
			clearCache(contentRelated);
		}

		return contentRelated;
	}

	@Override
	public ContentRelated updateImpl(ContentRelated contentRelated) {
		boolean isNew = contentRelated.isNew();

		if (!(contentRelated instanceof ContentRelatedModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(contentRelated.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(contentRelated);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in contentRelated proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ContentRelated implementation " +
				contentRelated.getClass());
		}

		ContentRelatedModelImpl contentRelatedModelImpl = (ContentRelatedModelImpl)contentRelated;

		if (Validator.isNull(contentRelated.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			contentRelated.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (contentRelated.isNew()) {
				session.save(contentRelated);

				contentRelated.setNew(false);
			}
			else {
				contentRelated = (ContentRelated)session.merge(contentRelated);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContentRelatedModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { contentRelatedModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { contentRelatedModelImpl.getContentParentId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTPARENTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPARENTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contentRelatedModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentRelatedModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { contentRelatedModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((contentRelatedModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPARENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentRelatedModelImpl.getOriginalContentParentId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTPARENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPARENTID,
					args);

				args = new Object[] { contentRelatedModelImpl.getContentParentId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTPARENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTPARENTID,
					args);
			}
		}

		entityCache.putResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
			ContentRelatedImpl.class, contentRelated.getPrimaryKey(),
			contentRelated, false);

		contentRelated.resetOriginalValues();

		return contentRelated;
	}

	/**
	 * Returns the content related with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the content related
	 * @return the content related
	 * @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentRelatedException {
		ContentRelated contentRelated = fetchByPrimaryKey(primaryKey);

		if (contentRelated == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contentRelated;
	}

	/**
	 * Returns the content related with the primary key or throws a {@link NoSuchContentRelatedException} if it could not be found.
	 *
	 * @param contentRelatedId the primary key of the content related
	 * @return the content related
	 * @throws NoSuchContentRelatedException if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated findByPrimaryKey(long contentRelatedId)
		throws NoSuchContentRelatedException {
		return findByPrimaryKey((Serializable)contentRelatedId);
	}

	/**
	 * Returns the content related with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the content related
	 * @return the content related, or <code>null</code> if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
				ContentRelatedImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContentRelated contentRelated = (ContentRelated)serializable;

		if (contentRelated == null) {
			Session session = null;

			try {
				session = openSession();

				contentRelated = (ContentRelated)session.get(ContentRelatedImpl.class,
						primaryKey);

				if (contentRelated != null) {
					cacheResult(contentRelated);
				}
				else {
					entityCache.putResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
						ContentRelatedImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
					ContentRelatedImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contentRelated;
	}

	/**
	 * Returns the content related with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentRelatedId the primary key of the content related
	 * @return the content related, or <code>null</code> if a content related with the primary key could not be found
	 */
	@Override
	public ContentRelated fetchByPrimaryKey(long contentRelatedId) {
		return fetchByPrimaryKey((Serializable)contentRelatedId);
	}

	@Override
	public Map<Serializable, ContentRelated> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContentRelated> map = new HashMap<Serializable, ContentRelated>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContentRelated contentRelated = fetchByPrimaryKey(primaryKey);

			if (contentRelated != null) {
				map.put(primaryKey, contentRelated);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
					ContentRelatedImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContentRelated)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTENTRELATED_WHERE_PKS_IN);

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

			for (ContentRelated contentRelated : (List<ContentRelated>)q.list()) {
				map.put(contentRelated.getPrimaryKeyObj(), contentRelated);

				cacheResult(contentRelated);

				uncachedPrimaryKeys.remove(contentRelated.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContentRelatedModelImpl.ENTITY_CACHE_ENABLED,
					ContentRelatedImpl.class, primaryKey, nullModel);
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
	 * Returns all the content relateds.
	 *
	 * @return the content relateds
	 */
	@Override
	public List<ContentRelated> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the content relateds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @return the range of content relateds
	 */
	@Override
	public List<ContentRelated> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the content relateds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of content relateds
	 */
	@Override
	public List<ContentRelated> findAll(int start, int end,
		OrderByComparator<ContentRelated> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the content relateds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of content relateds
	 * @param end the upper bound of the range of content relateds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of content relateds
	 */
	@Override
	public List<ContentRelated> findAll(int start, int end,
		OrderByComparator<ContentRelated> orderByComparator,
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

		List<ContentRelated> list = null;

		if (retrieveFromCache) {
			list = (List<ContentRelated>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTENTRELATED);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENTRELATED;

				if (pagination) {
					sql = sql.concat(ContentRelatedModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContentRelated>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContentRelated>)QueryUtil.list(q,
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
	 * Removes all the content relateds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContentRelated contentRelated : findAll()) {
			remove(contentRelated);
		}
	}

	/**
	 * Returns the number of content relateds.
	 *
	 * @return the number of content relateds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTENTRELATED);

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
		return ContentRelatedModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the content related persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContentRelatedImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONTENTRELATED = "SELECT contentRelated FROM ContentRelated contentRelated";
	private static final String _SQL_SELECT_CONTENTRELATED_WHERE_PKS_IN = "SELECT contentRelated FROM ContentRelated contentRelated WHERE contentRelatedId IN (";
	private static final String _SQL_SELECT_CONTENTRELATED_WHERE = "SELECT contentRelated FROM ContentRelated contentRelated WHERE ";
	private static final String _SQL_COUNT_CONTENTRELATED = "SELECT COUNT(contentRelated) FROM ContentRelated contentRelated";
	private static final String _SQL_COUNT_CONTENTRELATED_WHERE = "SELECT COUNT(contentRelated) FROM ContentRelated contentRelated WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contentRelated.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContentRelated exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContentRelated exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContentRelatedPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}
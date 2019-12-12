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

package es.aragon.base.semaphore.service.persistence.impl;

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

import es.aragon.base.semaphore.exception.NoSuchSemaphoreException;
import es.aragon.base.semaphore.model.Semaphore;
import es.aragon.base.semaphore.model.impl.SemaphoreImpl;
import es.aragon.base.semaphore.model.impl.SemaphoreModelImpl;
import es.aragon.base.semaphore.service.persistence.SemaphorePersistence;

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
 * The persistence implementation for the semaphore service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SemaphorePersistence
 * @see es.aragon.base.semaphore.service.persistence.SemaphoreUtil
 * @generated
 */
@ProviderType
public class SemaphorePersistenceImpl extends BasePersistenceImpl<Semaphore>
	implements SemaphorePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SemaphoreUtil} to access the semaphore persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SemaphoreImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SemaphoreModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the semaphores where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching semaphores
	 */
	@Override
	public List<Semaphore> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the semaphores where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @return the range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the semaphores where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByUuid(String uuid, int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the semaphores where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByUuid(String uuid, int start, int end,
		OrderByComparator<Semaphore> orderByComparator,
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

		List<Semaphore> list = null;

		if (retrieveFromCache) {
			list = (List<Semaphore>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Semaphore semaphore : list) {
					if (!Objects.equals(uuid, semaphore.getUuid())) {
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

			query.append(_SQL_SELECT_SEMAPHORE_WHERE);

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
				query.append(SemaphoreModelImpl.ORDER_BY_JPQL);
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
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first semaphore in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching semaphore
	 * @throws NoSuchSemaphoreException if a matching semaphore could not be found
	 */
	@Override
	public Semaphore findByUuid_First(String uuid,
		OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByUuid_First(uuid, orderByComparator);

		if (semaphore != null) {
			return semaphore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSemaphoreException(msg.toString());
	}

	/**
	 * Returns the first semaphore in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByUuid_First(String uuid,
		OrderByComparator<Semaphore> orderByComparator) {
		List<Semaphore> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last semaphore in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching semaphore
	 * @throws NoSuchSemaphoreException if a matching semaphore could not be found
	 */
	@Override
	public Semaphore findByUuid_Last(String uuid,
		OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByUuid_Last(uuid, orderByComparator);

		if (semaphore != null) {
			return semaphore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSemaphoreException(msg.toString());
	}

	/**
	 * Returns the last semaphore in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByUuid_Last(String uuid,
		OrderByComparator<Semaphore> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Semaphore> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the semaphores before and after the current semaphore in the ordered set where uuid = &#63;.
	 *
	 * @param semaphoreId the primary key of the current semaphore
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next semaphore
	 * @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore[] findByUuid_PrevAndNext(long semaphoreId, String uuid,
		OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = findByPrimaryKey(semaphoreId);

		Session session = null;

		try {
			session = openSession();

			Semaphore[] array = new SemaphoreImpl[3];

			array[0] = getByUuid_PrevAndNext(session, semaphore, uuid,
					orderByComparator, true);

			array[1] = semaphore;

			array[2] = getByUuid_PrevAndNext(session, semaphore, uuid,
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

	protected Semaphore getByUuid_PrevAndNext(Session session,
		Semaphore semaphore, String uuid,
		OrderByComparator<Semaphore> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SEMAPHORE_WHERE);

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
			query.append(SemaphoreModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(semaphore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Semaphore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the semaphores where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Semaphore semaphore : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(semaphore);
		}
	}

	/**
	 * Returns the number of semaphores where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching semaphores
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SEMAPHORE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "semaphore.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "semaphore.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(semaphore.uuid IS NULL OR semaphore.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SemaphoreModelImpl.UUID_COLUMN_BITMASK |
			SemaphoreModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the semaphore where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSemaphoreException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching semaphore
	 * @throws NoSuchSemaphoreException if a matching semaphore could not be found
	 */
	@Override
	public Semaphore findByUUID_G(String uuid, long groupId)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByUUID_G(uuid, groupId);

		if (semaphore == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSemaphoreException(msg.toString());
		}

		return semaphore;
	}

	/**
	 * Returns the semaphore where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the semaphore where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Semaphore) {
			Semaphore semaphore = (Semaphore)result;

			if (!Objects.equals(uuid, semaphore.getUuid()) ||
					(groupId != semaphore.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SEMAPHORE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Semaphore> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Semaphore semaphore = list.get(0);

					result = semaphore;

					cacheResult(semaphore);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (Semaphore)result;
		}
	}

	/**
	 * Removes the semaphore where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the semaphore that was removed
	 */
	@Override
	public Semaphore removeByUUID_G(String uuid, long groupId)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = findByUUID_G(uuid, groupId);

		return remove(semaphore);
	}

	/**
	 * Returns the number of semaphores where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching semaphores
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SEMAPHORE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "semaphore.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "semaphore.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(semaphore.uuid IS NULL OR semaphore.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "semaphore.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICENAMEGROUPID =
		new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByServiceNameGroupId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICENAMEGROUPID =
		new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByServiceNameGroupId",
			new String[] { Long.class.getName(), String.class.getName() },
			SemaphoreModelImpl.GROUPID_COLUMN_BITMASK |
			SemaphoreModelImpl.SERVICENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SERVICENAMEGROUPID = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByServiceNameGroupId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the semaphores where groupId = &#63; and serviceName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @return the matching semaphores
	 */
	@Override
	public List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName) {
		return findByServiceNameGroupId(groupId, serviceName,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the semaphores where groupId = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @return the range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end) {
		return findByServiceNameGroupId(groupId, serviceName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the semaphores where groupId = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return findByServiceNameGroupId(groupId, serviceName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the semaphores where groupId = &#63; and serviceName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByServiceNameGroupId(long groupId,
		String serviceName, int start, int end,
		OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICENAMEGROUPID;
			finderArgs = new Object[] { groupId, serviceName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SERVICENAMEGROUPID;
			finderArgs = new Object[] {
					groupId, serviceName,
					
					start, end, orderByComparator
				};
		}

		List<Semaphore> list = null;

		if (retrieveFromCache) {
			list = (List<Semaphore>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Semaphore semaphore : list) {
					if ((groupId != semaphore.getGroupId()) ||
							!Objects.equals(serviceName,
								semaphore.getServiceName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SEMAPHORE_WHERE);

			query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_GROUPID_2);

			boolean bindServiceName = false;

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_1);
			}
			else if (serviceName.equals("")) {
				query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_3);
			}
			else {
				bindServiceName = true;

				query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SemaphoreModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceName) {
					qPos.add(serviceName);
				}

				if (!pagination) {
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching semaphore
	 * @throws NoSuchSemaphoreException if a matching semaphore could not be found
	 */
	@Override
	public Semaphore findByServiceNameGroupId_First(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByServiceNameGroupId_First(groupId,
				serviceName, orderByComparator);

		if (semaphore != null) {
			return semaphore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceName=");
		msg.append(serviceName);

		msg.append("}");

		throw new NoSuchSemaphoreException(msg.toString());
	}

	/**
	 * Returns the first semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByServiceNameGroupId_First(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator) {
		List<Semaphore> list = findByServiceNameGroupId(groupId, serviceName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching semaphore
	 * @throws NoSuchSemaphoreException if a matching semaphore could not be found
	 */
	@Override
	public Semaphore findByServiceNameGroupId_Last(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByServiceNameGroupId_Last(groupId,
				serviceName, orderByComparator);

		if (semaphore != null) {
			return semaphore;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceName=");
		msg.append(serviceName);

		msg.append("}");

		throw new NoSuchSemaphoreException(msg.toString());
	}

	/**
	 * Returns the last semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByServiceNameGroupId_Last(long groupId,
		String serviceName, OrderByComparator<Semaphore> orderByComparator) {
		int count = countByServiceNameGroupId(groupId, serviceName);

		if (count == 0) {
			return null;
		}

		List<Semaphore> list = findByServiceNameGroupId(groupId, serviceName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the semaphores before and after the current semaphore in the ordered set where groupId = &#63; and serviceName = &#63;.
	 *
	 * @param semaphoreId the primary key of the current semaphore
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next semaphore
	 * @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore[] findByServiceNameGroupId_PrevAndNext(long semaphoreId,
		long groupId, String serviceName,
		OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = findByPrimaryKey(semaphoreId);

		Session session = null;

		try {
			session = openSession();

			Semaphore[] array = new SemaphoreImpl[3];

			array[0] = getByServiceNameGroupId_PrevAndNext(session, semaphore,
					groupId, serviceName, orderByComparator, true);

			array[1] = semaphore;

			array[2] = getByServiceNameGroupId_PrevAndNext(session, semaphore,
					groupId, serviceName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Semaphore getByServiceNameGroupId_PrevAndNext(Session session,
		Semaphore semaphore, long groupId, String serviceName,
		OrderByComparator<Semaphore> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SEMAPHORE_WHERE);

		query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_GROUPID_2);

		boolean bindServiceName = false;

		if (serviceName == null) {
			query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_1);
		}
		else if (serviceName.equals("")) {
			query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_3);
		}
		else {
			bindServiceName = true;

			query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_2);
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
			query.append(SemaphoreModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServiceName) {
			qPos.add(serviceName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(semaphore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Semaphore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the semaphores where groupId = &#63; and serviceName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 */
	@Override
	public void removeByServiceNameGroupId(long groupId, String serviceName) {
		for (Semaphore semaphore : findByServiceNameGroupId(groupId,
				serviceName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(semaphore);
		}
	}

	/**
	 * Returns the number of semaphores where groupId = &#63; and serviceName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceName the service name
	 * @return the number of matching semaphores
	 */
	@Override
	public int countByServiceNameGroupId(long groupId, String serviceName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SERVICENAMEGROUPID;

		Object[] finderArgs = new Object[] { groupId, serviceName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SEMAPHORE_WHERE);

			query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_GROUPID_2);

			boolean bindServiceName = false;

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_1);
			}
			else if (serviceName.equals("")) {
				query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_3);
			}
			else {
				bindServiceName = true;

				query.append(_FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceName) {
					qPos.add(serviceName);
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

	private static final String _FINDER_COLUMN_SERVICENAMEGROUPID_GROUPID_2 = "semaphore.groupId = ? AND ";
	private static final String _FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_1 = "semaphore.serviceName IS NULL";
	private static final String _FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_2 = "semaphore.serviceName = ?";
	private static final String _FINDER_COLUMN_SERVICENAMEGROUPID_SERVICENAME_3 = "(semaphore.serviceName IS NULL OR semaphore.serviceName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, SemaphoreImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SemaphoreModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the semaphores where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching semaphores
	 */
	@Override
	public List<Semaphore> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the semaphores where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @return the range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the semaphores where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the semaphores where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching semaphores
	 */
	@Override
	public List<Semaphore> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Semaphore> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Semaphore> list = null;

		if (retrieveFromCache) {
			list = (List<Semaphore>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Semaphore semaphore : list) {
					if ((groupId != semaphore.getGroupId())) {
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

			query.append(_SQL_SELECT_SEMAPHORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SemaphoreModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first semaphore in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching semaphore
	 * @throws NoSuchSemaphoreException if a matching semaphore could not be found
	 */
	@Override
	public Semaphore findByGroupId_First(long groupId,
		OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByGroupId_First(groupId, orderByComparator);

		if (semaphore != null) {
			return semaphore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSemaphoreException(msg.toString());
	}

	/**
	 * Returns the first semaphore in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByGroupId_First(long groupId,
		OrderByComparator<Semaphore> orderByComparator) {
		List<Semaphore> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last semaphore in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching semaphore
	 * @throws NoSuchSemaphoreException if a matching semaphore could not be found
	 */
	@Override
	public Semaphore findByGroupId_Last(long groupId,
		OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByGroupId_Last(groupId, orderByComparator);

		if (semaphore != null) {
			return semaphore;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSemaphoreException(msg.toString());
	}

	/**
	 * Returns the last semaphore in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching semaphore, or <code>null</code> if a matching semaphore could not be found
	 */
	@Override
	public Semaphore fetchByGroupId_Last(long groupId,
		OrderByComparator<Semaphore> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Semaphore> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the semaphores before and after the current semaphore in the ordered set where groupId = &#63;.
	 *
	 * @param semaphoreId the primary key of the current semaphore
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next semaphore
	 * @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore[] findByGroupId_PrevAndNext(long semaphoreId,
		long groupId, OrderByComparator<Semaphore> orderByComparator)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = findByPrimaryKey(semaphoreId);

		Session session = null;

		try {
			session = openSession();

			Semaphore[] array = new SemaphoreImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, semaphore, groupId,
					orderByComparator, true);

			array[1] = semaphore;

			array[2] = getByGroupId_PrevAndNext(session, semaphore, groupId,
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

	protected Semaphore getByGroupId_PrevAndNext(Session session,
		Semaphore semaphore, long groupId,
		OrderByComparator<Semaphore> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SEMAPHORE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(SemaphoreModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(semaphore);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Semaphore> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the semaphores where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Semaphore semaphore : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(semaphore);
		}
	}

	/**
	 * Returns the number of semaphores where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching semaphores
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SEMAPHORE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "semaphore.groupId = ?";

	public SemaphorePersistenceImpl() {
		setModelClass(Semaphore.class);

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
	 * Caches the semaphore in the entity cache if it is enabled.
	 *
	 * @param semaphore the semaphore
	 */
	@Override
	public void cacheResult(Semaphore semaphore) {
		entityCache.putResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreImpl.class, semaphore.getPrimaryKey(), semaphore);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { semaphore.getUuid(), semaphore.getGroupId() },
			semaphore);

		semaphore.resetOriginalValues();
	}

	/**
	 * Caches the semaphores in the entity cache if it is enabled.
	 *
	 * @param semaphores the semaphores
	 */
	@Override
	public void cacheResult(List<Semaphore> semaphores) {
		for (Semaphore semaphore : semaphores) {
			if (entityCache.getResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
						SemaphoreImpl.class, semaphore.getPrimaryKey()) == null) {
				cacheResult(semaphore);
			}
			else {
				semaphore.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all semaphores.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SemaphoreImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the semaphore.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Semaphore semaphore) {
		entityCache.removeResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreImpl.class, semaphore.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SemaphoreModelImpl)semaphore, true);
	}

	@Override
	public void clearCache(List<Semaphore> semaphores) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Semaphore semaphore : semaphores) {
			entityCache.removeResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
				SemaphoreImpl.class, semaphore.getPrimaryKey());

			clearUniqueFindersCache((SemaphoreModelImpl)semaphore, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SemaphoreModelImpl semaphoreModelImpl) {
		Object[] args = new Object[] {
				semaphoreModelImpl.getUuid(), semaphoreModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			semaphoreModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SemaphoreModelImpl semaphoreModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					semaphoreModelImpl.getUuid(),
					semaphoreModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((semaphoreModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					semaphoreModelImpl.getOriginalUuid(),
					semaphoreModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new semaphore with the primary key. Does not add the semaphore to the database.
	 *
	 * @param semaphoreId the primary key for the new semaphore
	 * @return the new semaphore
	 */
	@Override
	public Semaphore create(long semaphoreId) {
		Semaphore semaphore = new SemaphoreImpl();

		semaphore.setNew(true);
		semaphore.setPrimaryKey(semaphoreId);

		String uuid = PortalUUIDUtil.generate();

		semaphore.setUuid(uuid);

		return semaphore;
	}

	/**
	 * Removes the semaphore with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param semaphoreId the primary key of the semaphore
	 * @return the semaphore that was removed
	 * @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore remove(long semaphoreId) throws NoSuchSemaphoreException {
		return remove((Serializable)semaphoreId);
	}

	/**
	 * Removes the semaphore with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the semaphore
	 * @return the semaphore that was removed
	 * @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore remove(Serializable primaryKey)
		throws NoSuchSemaphoreException {
		Session session = null;

		try {
			session = openSession();

			Semaphore semaphore = (Semaphore)session.get(SemaphoreImpl.class,
					primaryKey);

			if (semaphore == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSemaphoreException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(semaphore);
		}
		catch (NoSuchSemaphoreException nsee) {
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
	protected Semaphore removeImpl(Semaphore semaphore) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(semaphore)) {
				semaphore = (Semaphore)session.get(SemaphoreImpl.class,
						semaphore.getPrimaryKeyObj());
			}

			if (semaphore != null) {
				session.delete(semaphore);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (semaphore != null) {
			clearCache(semaphore);
		}

		return semaphore;
	}

	@Override
	public Semaphore updateImpl(Semaphore semaphore) {
		boolean isNew = semaphore.isNew();

		if (!(semaphore instanceof SemaphoreModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(semaphore.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(semaphore);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in semaphore proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Semaphore implementation " +
				semaphore.getClass());
		}

		SemaphoreModelImpl semaphoreModelImpl = (SemaphoreModelImpl)semaphore;

		if (Validator.isNull(semaphore.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			semaphore.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (semaphore.isNew()) {
				session.save(semaphore);

				semaphore.setNew(false);
			}
			else {
				semaphore = (Semaphore)session.merge(semaphore);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SemaphoreModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { semaphoreModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					semaphoreModelImpl.getGroupId(),
					semaphoreModelImpl.getServiceName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICENAMEGROUPID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICENAMEGROUPID,
				args);

			args = new Object[] { semaphoreModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((semaphoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						semaphoreModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { semaphoreModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((semaphoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICENAMEGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						semaphoreModelImpl.getOriginalGroupId(),
						semaphoreModelImpl.getOriginalServiceName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICENAMEGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICENAMEGROUPID,
					args);

				args = new Object[] {
						semaphoreModelImpl.getGroupId(),
						semaphoreModelImpl.getServiceName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVICENAMEGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERVICENAMEGROUPID,
					args);
			}

			if ((semaphoreModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						semaphoreModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { semaphoreModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
			SemaphoreImpl.class, semaphore.getPrimaryKey(), semaphore, false);

		clearUniqueFindersCache(semaphoreModelImpl, false);
		cacheUniqueFindersCache(semaphoreModelImpl);

		semaphore.resetOriginalValues();

		return semaphore;
	}

	/**
	 * Returns the semaphore with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the semaphore
	 * @return the semaphore
	 * @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSemaphoreException {
		Semaphore semaphore = fetchByPrimaryKey(primaryKey);

		if (semaphore == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSemaphoreException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return semaphore;
	}

	/**
	 * Returns the semaphore with the primary key or throws a {@link NoSuchSemaphoreException} if it could not be found.
	 *
	 * @param semaphoreId the primary key of the semaphore
	 * @return the semaphore
	 * @throws NoSuchSemaphoreException if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore findByPrimaryKey(long semaphoreId)
		throws NoSuchSemaphoreException {
		return findByPrimaryKey((Serializable)semaphoreId);
	}

	/**
	 * Returns the semaphore with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the semaphore
	 * @return the semaphore, or <code>null</code> if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
				SemaphoreImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Semaphore semaphore = (Semaphore)serializable;

		if (semaphore == null) {
			Session session = null;

			try {
				session = openSession();

				semaphore = (Semaphore)session.get(SemaphoreImpl.class,
						primaryKey);

				if (semaphore != null) {
					cacheResult(semaphore);
				}
				else {
					entityCache.putResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
						SemaphoreImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
					SemaphoreImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return semaphore;
	}

	/**
	 * Returns the semaphore with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param semaphoreId the primary key of the semaphore
	 * @return the semaphore, or <code>null</code> if a semaphore with the primary key could not be found
	 */
	@Override
	public Semaphore fetchByPrimaryKey(long semaphoreId) {
		return fetchByPrimaryKey((Serializable)semaphoreId);
	}

	@Override
	public Map<Serializable, Semaphore> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Semaphore> map = new HashMap<Serializable, Semaphore>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Semaphore semaphore = fetchByPrimaryKey(primaryKey);

			if (semaphore != null) {
				map.put(primaryKey, semaphore);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
					SemaphoreImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Semaphore)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SEMAPHORE_WHERE_PKS_IN);

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

			for (Semaphore semaphore : (List<Semaphore>)q.list()) {
				map.put(semaphore.getPrimaryKeyObj(), semaphore);

				cacheResult(semaphore);

				uncachedPrimaryKeys.remove(semaphore.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SemaphoreModelImpl.ENTITY_CACHE_ENABLED,
					SemaphoreImpl.class, primaryKey, nullModel);
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
	 * Returns all the semaphores.
	 *
	 * @return the semaphores
	 */
	@Override
	public List<Semaphore> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the semaphores.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @return the range of semaphores
	 */
	@Override
	public List<Semaphore> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the semaphores.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of semaphores
	 */
	@Override
	public List<Semaphore> findAll(int start, int end,
		OrderByComparator<Semaphore> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the semaphores.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SemaphoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of semaphores
	 * @param end the upper bound of the range of semaphores (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of semaphores
	 */
	@Override
	public List<Semaphore> findAll(int start, int end,
		OrderByComparator<Semaphore> orderByComparator,
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

		List<Semaphore> list = null;

		if (retrieveFromCache) {
			list = (List<Semaphore>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SEMAPHORE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SEMAPHORE;

				if (pagination) {
					sql = sql.concat(SemaphoreModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Semaphore>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the semaphores from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Semaphore semaphore : findAll()) {
			remove(semaphore);
		}
	}

	/**
	 * Returns the number of semaphores.
	 *
	 * @return the number of semaphores
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SEMAPHORE);

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
		return SemaphoreModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the semaphore persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SemaphoreImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SEMAPHORE = "SELECT semaphore FROM Semaphore semaphore";
	private static final String _SQL_SELECT_SEMAPHORE_WHERE_PKS_IN = "SELECT semaphore FROM Semaphore semaphore WHERE semaphoreId IN (";
	private static final String _SQL_SELECT_SEMAPHORE_WHERE = "SELECT semaphore FROM Semaphore semaphore WHERE ";
	private static final String _SQL_COUNT_SEMAPHORE = "SELECT COUNT(semaphore) FROM Semaphore semaphore";
	private static final String _SQL_COUNT_SEMAPHORE_WHERE = "SELECT COUNT(semaphore) FROM Semaphore semaphore WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "semaphore.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Semaphore exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Semaphore exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SemaphorePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}
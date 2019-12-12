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

package es.aragon.base.process.queue.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import es.aragon.base.process.queue.exception.NoSuchQueuedProcessException;
import es.aragon.base.process.queue.model.QueuedProcess;
import es.aragon.base.process.queue.model.impl.QueuedProcessImpl;
import es.aragon.base.process.queue.model.impl.QueuedProcessModelImpl;
import es.aragon.base.process.queue.service.persistence.QueuedProcessPersistence;

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
 * The persistence implementation for the queued process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedProcessPersistence
 * @see es.aragon.base.process.queue.service.persistence.QueuedProcessUtil
 * @generated
 */
@ProviderType
public class QueuedProcessPersistenceImpl extends BasePersistenceImpl<QueuedProcess>
	implements QueuedProcessPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link QueuedProcessUtil} to access the queued process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = QueuedProcessImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			QueuedProcessModelImpl.UUID_COLUMN_BITMASK |
			QueuedProcessModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the queued processes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the queued processes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @return the range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the queued processes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid(String uuid, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the queued processes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid(String uuid, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator,
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

		List<QueuedProcess> list = null;

		if (retrieveFromCache) {
			list = (List<QueuedProcess>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (QueuedProcess queuedProcess : list) {
					if (!Objects.equals(uuid, queuedProcess.getUuid())) {
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

			query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

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
				query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
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
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first queued process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByUuid_First(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByUuid_First(uuid, orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the first queued process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByUuid_First(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator) {
		List<QueuedProcess> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last queued process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByUuid_Last(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByUuid_Last(uuid, orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the last queued process in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByUuid_Last(String uuid,
		OrderByComparator<QueuedProcess> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<QueuedProcess> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the queued processes before and after the current queued process in the ordered set where uuid = &#63;.
	 *
	 * @param queuedProcessId the primary key of the current queued process
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next queued process
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess[] findByUuid_PrevAndNext(long queuedProcessId,
		String uuid, OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = findByPrimaryKey(queuedProcessId);

		Session session = null;

		try {
			session = openSession();

			QueuedProcess[] array = new QueuedProcessImpl[3];

			array[0] = getByUuid_PrevAndNext(session, queuedProcess, uuid,
					orderByComparator, true);

			array[1] = queuedProcess;

			array[2] = getByUuid_PrevAndNext(session, queuedProcess, uuid,
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

	protected QueuedProcess getByUuid_PrevAndNext(Session session,
		QueuedProcess queuedProcess, String uuid,
		OrderByComparator<QueuedProcess> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

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
			query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(queuedProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<QueuedProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the queued processes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (QueuedProcess queuedProcess : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(queuedProcess);
		}
	}

	/**
	 * Returns the number of queued processes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching queued processes
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_QUEUEDPROCESS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "queuedProcess.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "queuedProcess.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(queuedProcess.uuid IS NULL OR queuedProcess.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			QueuedProcessModelImpl.UUID_COLUMN_BITMASK |
			QueuedProcessModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the queued process where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchQueuedProcessException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByUUID_G(String uuid, long groupId)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByUUID_G(uuid, groupId);

		if (queuedProcess == null) {
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

			throw new NoSuchQueuedProcessException(msg.toString());
		}

		return queuedProcess;
	}

	/**
	 * Returns the queued process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the queued process where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof QueuedProcess) {
			QueuedProcess queuedProcess = (QueuedProcess)result;

			if (!Objects.equals(uuid, queuedProcess.getUuid()) ||
					(groupId != queuedProcess.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

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

				List<QueuedProcess> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					QueuedProcess queuedProcess = list.get(0);

					result = queuedProcess;

					cacheResult(queuedProcess);
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
			return (QueuedProcess)result;
		}
	}

	/**
	 * Removes the queued process where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the queued process that was removed
	 */
	@Override
	public QueuedProcess removeByUUID_G(String uuid, long groupId)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = findByUUID_G(uuid, groupId);

		return remove(queuedProcess);
	}

	/**
	 * Returns the number of queued processes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching queued processes
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_QUEUEDPROCESS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "queuedProcess.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "queuedProcess.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(queuedProcess.uuid IS NULL OR queuedProcess.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "queuedProcess.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			QueuedProcessModelImpl.UUID_COLUMN_BITMASK |
			QueuedProcessModelImpl.COMPANYID_COLUMN_BITMASK |
			QueuedProcessModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the queued processes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the queued processes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @return the range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the queued processes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<QueuedProcess> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the queued processes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<QueuedProcess> list = null;

		if (retrieveFromCache) {
			list = (List<QueuedProcess>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (QueuedProcess queuedProcess : list) {
					if (!Objects.equals(uuid, queuedProcess.getUuid()) ||
							(companyId != queuedProcess.getCompanyId())) {
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

			query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
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

				qPos.add(companyId);

				if (!pagination) {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the first queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator) {
		List<QueuedProcess> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the last queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<QueuedProcess> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the queued processes before and after the current queued process in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param queuedProcessId the primary key of the current queued process
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next queued process
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess[] findByUuid_C_PrevAndNext(long queuedProcessId,
		String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = findByPrimaryKey(queuedProcessId);

		Session session = null;

		try {
			session = openSession();

			QueuedProcess[] array = new QueuedProcessImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, queuedProcess, uuid,
					companyId, orderByComparator, true);

			array[1] = queuedProcess;

			array[2] = getByUuid_C_PrevAndNext(session, queuedProcess, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected QueuedProcess getByUuid_C_PrevAndNext(Session session,
		QueuedProcess queuedProcess, String uuid, long companyId,
		OrderByComparator<QueuedProcess> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(queuedProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<QueuedProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the queued processes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (QueuedProcess queuedProcess : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(queuedProcess);
		}
	}

	/**
	 * Returns the number of queued processes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching queued processes
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_QUEUEDPROCESS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "queuedProcess.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "queuedProcess.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(queuedProcess.uuid IS NULL OR queuedProcess.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "queuedProcess.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROCESSNAMEGROUPID =
		new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByProcessNameGroupId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPID =
		new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByProcessNameGroupId",
			new String[] { Long.class.getName(), String.class.getName() },
			QueuedProcessModelImpl.GROUPID_COLUMN_BITMASK |
			QueuedProcessModelImpl.PROCESSNAME_COLUMN_BITMASK |
			QueuedProcessModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPID = new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByProcessNameGroupId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the queued processes where groupId = &#63; and processName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @return the matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName) {
		return findByProcessNameGroupId(groupId, processName,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the queued processes where groupId = &#63; and processName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @return the range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName, int start, int end) {
		return findByProcessNameGroupId(groupId, processName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return findByProcessNameGroupId(groupId, processName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupId(long groupId,
		String processName, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPID;
			finderArgs = new Object[] { groupId, processName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROCESSNAMEGROUPID;
			finderArgs = new Object[] {
					groupId, processName,
					
					start, end, orderByComparator
				};
		}

		List<QueuedProcess> list = null;

		if (retrieveFromCache) {
			list = (List<QueuedProcess>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (QueuedProcess queuedProcess : list) {
					if ((groupId != queuedProcess.getGroupId()) ||
							!Objects.equals(processName,
								queuedProcess.getProcessName())) {
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

			query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_GROUPID_2);

			boolean bindProcessName = false;

			if (processName == null) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_1);
			}
			else if (processName.equals("")) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_3);
			}
			else {
				bindProcessName = true;

				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProcessName) {
					qPos.add(processName);
				}

				if (!pagination) {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByProcessNameGroupId_First(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByProcessNameGroupId_First(groupId,
				processName, orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processName=");
		msg.append(processName);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByProcessNameGroupId_First(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator) {
		List<QueuedProcess> list = findByProcessNameGroupId(groupId,
				processName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByProcessNameGroupId_Last(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByProcessNameGroupId_Last(groupId,
				processName, orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processName=");
		msg.append(processName);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByProcessNameGroupId_Last(long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator) {
		int count = countByProcessNameGroupId(groupId, processName);

		if (count == 0) {
			return null;
		}

		List<QueuedProcess> list = findByProcessNameGroupId(groupId,
				processName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the queued processes before and after the current queued process in the ordered set where groupId = &#63; and processName = &#63;.
	 *
	 * @param queuedProcessId the primary key of the current queued process
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next queued process
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess[] findByProcessNameGroupId_PrevAndNext(
		long queuedProcessId, long groupId, String processName,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = findByPrimaryKey(queuedProcessId);

		Session session = null;

		try {
			session = openSession();

			QueuedProcess[] array = new QueuedProcessImpl[3];

			array[0] = getByProcessNameGroupId_PrevAndNext(session,
					queuedProcess, groupId, processName, orderByComparator, true);

			array[1] = queuedProcess;

			array[2] = getByProcessNameGroupId_PrevAndNext(session,
					queuedProcess, groupId, processName, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected QueuedProcess getByProcessNameGroupId_PrevAndNext(
		Session session, QueuedProcess queuedProcess, long groupId,
		String processName, OrderByComparator<QueuedProcess> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

		query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_GROUPID_2);

		boolean bindProcessName = false;

		if (processName == null) {
			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_1);
		}
		else if (processName.equals("")) {
			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_3);
		}
		else {
			bindProcessName = true;

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_2);
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
			query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindProcessName) {
			qPos.add(processName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(queuedProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<QueuedProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the queued processes where groupId = &#63; and processName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 */
	@Override
	public void removeByProcessNameGroupId(long groupId, String processName) {
		for (QueuedProcess queuedProcess : findByProcessNameGroupId(groupId,
				processName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(queuedProcess);
		}
	}

	/**
	 * Returns the number of queued processes where groupId = &#63; and processName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @return the number of matching queued processes
	 */
	@Override
	public int countByProcessNameGroupId(long groupId, String processName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPID;

		Object[] finderArgs = new Object[] { groupId, processName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_QUEUEDPROCESS_WHERE);

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_GROUPID_2);

			boolean bindProcessName = false;

			if (processName == null) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_1);
			}
			else if (processName.equals("")) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_3);
			}
			else {
				bindProcessName = true;

				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProcessName) {
					qPos.add(processName);
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

	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPID_GROUPID_2 = "queuedProcess.groupId = ? AND ";
	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_1 = "queuedProcess.processName IS NULL";
	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_2 = "queuedProcess.processName = ?";
	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPID_PROCESSNAME_3 = "(queuedProcess.processName IS NULL OR queuedProcess.processName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS =
		new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByProcessNameGroupIdStatus",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS =
		new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED,
			QueuedProcessImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByProcessNameGroupIdStatus",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			QueuedProcessModelImpl.GROUPID_COLUMN_BITMASK |
			QueuedProcessModelImpl.PROCESSNAME_COLUMN_BITMASK |
			QueuedProcessModelImpl.STATUS_COLUMN_BITMASK |
			QueuedProcessModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPIDSTATUS =
		new FinderPath(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByProcessNameGroupIdStatus",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @return the matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupIdStatus(long groupId,
		String processName, int status) {
		return findByProcessNameGroupIdStatus(groupId, processName, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @return the range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupIdStatus(long groupId,
		String processName, int status, int start, int end) {
		return findByProcessNameGroupIdStatus(groupId, processName, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupIdStatus(long groupId,
		String processName, int status, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return findByProcessNameGroupIdStatus(groupId, processName, status,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching queued processes
	 */
	@Override
	public List<QueuedProcess> findByProcessNameGroupIdStatus(long groupId,
		String processName, int status, int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS;
			finderArgs = new Object[] { groupId, processName, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS;
			finderArgs = new Object[] {
					groupId, processName, status,
					
					start, end, orderByComparator
				};
		}

		List<QueuedProcess> list = null;

		if (retrieveFromCache) {
			list = (List<QueuedProcess>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (QueuedProcess queuedProcess : list) {
					if ((groupId != queuedProcess.getGroupId()) ||
							!Objects.equals(processName,
								queuedProcess.getProcessName()) ||
							(status != queuedProcess.getStatus())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_GROUPID_2);

			boolean bindProcessName = false;

			if (processName == null) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_1);
			}
			else if (processName.equals("")) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_3);
			}
			else {
				bindProcessName = true;

				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_2);
			}

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProcessName) {
					qPos.add(processName);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByProcessNameGroupIdStatus_First(long groupId,
		String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByProcessNameGroupIdStatus_First(groupId,
				processName, status, orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processName=");
		msg.append(processName);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the first queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByProcessNameGroupIdStatus_First(long groupId,
		String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator) {
		List<QueuedProcess> list = findByProcessNameGroupIdStatus(groupId,
				processName, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process
	 * @throws NoSuchQueuedProcessException if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess findByProcessNameGroupIdStatus_Last(long groupId,
		String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByProcessNameGroupIdStatus_Last(groupId,
				processName, status, orderByComparator);

		if (queuedProcess != null) {
			return queuedProcess;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", processName=");
		msg.append(processName);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchQueuedProcessException(msg.toString());
	}

	/**
	 * Returns the last queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued process, or <code>null</code> if a matching queued process could not be found
	 */
	@Override
	public QueuedProcess fetchByProcessNameGroupIdStatus_Last(long groupId,
		String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator) {
		int count = countByProcessNameGroupIdStatus(groupId, processName, status);

		if (count == 0) {
			return null;
		}

		List<QueuedProcess> list = findByProcessNameGroupIdStatus(groupId,
				processName, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the queued processes before and after the current queued process in the ordered set where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * @param queuedProcessId the primary key of the current queued process
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next queued process
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess[] findByProcessNameGroupIdStatus_PrevAndNext(
		long queuedProcessId, long groupId, String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = findByPrimaryKey(queuedProcessId);

		Session session = null;

		try {
			session = openSession();

			QueuedProcess[] array = new QueuedProcessImpl[3];

			array[0] = getByProcessNameGroupIdStatus_PrevAndNext(session,
					queuedProcess, groupId, processName, status,
					orderByComparator, true);

			array[1] = queuedProcess;

			array[2] = getByProcessNameGroupIdStatus_PrevAndNext(session,
					queuedProcess, groupId, processName, status,
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

	protected QueuedProcess getByProcessNameGroupIdStatus_PrevAndNext(
		Session session, QueuedProcess queuedProcess, long groupId,
		String processName, int status,
		OrderByComparator<QueuedProcess> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE);

		query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_GROUPID_2);

		boolean bindProcessName = false;

		if (processName == null) {
			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_1);
		}
		else if (processName.equals("")) {
			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_3);
		}
		else {
			bindProcessName = true;

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_2);
		}

		query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_STATUS_2);

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
			query.append(QueuedProcessModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindProcessName) {
			qPos.add(processName);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(queuedProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<QueuedProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the queued processes where groupId = &#63; and processName = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 */
	@Override
	public void removeByProcessNameGroupIdStatus(long groupId,
		String processName, int status) {
		for (QueuedProcess queuedProcess : findByProcessNameGroupIdStatus(
				groupId, processName, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(queuedProcess);
		}
	}

	/**
	 * Returns the number of queued processes where groupId = &#63; and processName = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param processName the process name
	 * @param status the status
	 * @return the number of matching queued processes
	 */
	@Override
	public int countByProcessNameGroupIdStatus(long groupId,
		String processName, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPIDSTATUS;

		Object[] finderArgs = new Object[] { groupId, processName, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_QUEUEDPROCESS_WHERE);

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_GROUPID_2);

			boolean bindProcessName = false;

			if (processName == null) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_1);
			}
			else if (processName.equals("")) {
				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_3);
			}
			else {
				bindProcessName = true;

				query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_2);
			}

			query.append(_FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProcessName) {
					qPos.add(processName);
				}

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_GROUPID_2 =
		"queuedProcess.groupId = ? AND ";
	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_1 =
		"queuedProcess.processName IS NULL AND ";
	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_2 =
		"queuedProcess.processName = ? AND ";
	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_PROCESSNAME_3 =
		"(queuedProcess.processName IS NULL OR queuedProcess.processName = '') AND ";
	private static final String _FINDER_COLUMN_PROCESSNAMEGROUPIDSTATUS_STATUS_2 =
		"queuedProcess.status = ?";

	public QueuedProcessPersistenceImpl() {
		setModelClass(QueuedProcess.class);

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
	 * Caches the queued process in the entity cache if it is enabled.
	 *
	 * @param queuedProcess the queued process
	 */
	@Override
	public void cacheResult(QueuedProcess queuedProcess) {
		entityCache.putResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessImpl.class, queuedProcess.getPrimaryKey(),
			queuedProcess);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { queuedProcess.getUuid(), queuedProcess.getGroupId() },
			queuedProcess);

		queuedProcess.resetOriginalValues();
	}

	/**
	 * Caches the queued processes in the entity cache if it is enabled.
	 *
	 * @param queuedProcesses the queued processes
	 */
	@Override
	public void cacheResult(List<QueuedProcess> queuedProcesses) {
		for (QueuedProcess queuedProcess : queuedProcesses) {
			if (entityCache.getResult(
						QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
						QueuedProcessImpl.class, queuedProcess.getPrimaryKey()) == null) {
				cacheResult(queuedProcess);
			}
			else {
				queuedProcess.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all queued processes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(QueuedProcessImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the queued process.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(QueuedProcess queuedProcess) {
		entityCache.removeResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessImpl.class, queuedProcess.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((QueuedProcessModelImpl)queuedProcess, true);
	}

	@Override
	public void clearCache(List<QueuedProcess> queuedProcesses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (QueuedProcess queuedProcess : queuedProcesses) {
			entityCache.removeResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
				QueuedProcessImpl.class, queuedProcess.getPrimaryKey());

			clearUniqueFindersCache((QueuedProcessModelImpl)queuedProcess, true);
		}
	}

	protected void cacheUniqueFindersCache(
		QueuedProcessModelImpl queuedProcessModelImpl) {
		Object[] args = new Object[] {
				queuedProcessModelImpl.getUuid(),
				queuedProcessModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			queuedProcessModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		QueuedProcessModelImpl queuedProcessModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					queuedProcessModelImpl.getUuid(),
					queuedProcessModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((queuedProcessModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					queuedProcessModelImpl.getOriginalUuid(),
					queuedProcessModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new queued process with the primary key. Does not add the queued process to the database.
	 *
	 * @param queuedProcessId the primary key for the new queued process
	 * @return the new queued process
	 */
	@Override
	public QueuedProcess create(long queuedProcessId) {
		QueuedProcess queuedProcess = new QueuedProcessImpl();

		queuedProcess.setNew(true);
		queuedProcess.setPrimaryKey(queuedProcessId);

		String uuid = PortalUUIDUtil.generate();

		queuedProcess.setUuid(uuid);

		queuedProcess.setCompanyId(companyProvider.getCompanyId());

		return queuedProcess;
	}

	/**
	 * Removes the queued process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param queuedProcessId the primary key of the queued process
	 * @return the queued process that was removed
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess remove(long queuedProcessId)
		throws NoSuchQueuedProcessException {
		return remove((Serializable)queuedProcessId);
	}

	/**
	 * Removes the queued process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the queued process
	 * @return the queued process that was removed
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess remove(Serializable primaryKey)
		throws NoSuchQueuedProcessException {
		Session session = null;

		try {
			session = openSession();

			QueuedProcess queuedProcess = (QueuedProcess)session.get(QueuedProcessImpl.class,
					primaryKey);

			if (queuedProcess == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQueuedProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(queuedProcess);
		}
		catch (NoSuchQueuedProcessException nsee) {
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
	protected QueuedProcess removeImpl(QueuedProcess queuedProcess) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(queuedProcess)) {
				queuedProcess = (QueuedProcess)session.get(QueuedProcessImpl.class,
						queuedProcess.getPrimaryKeyObj());
			}

			if (queuedProcess != null) {
				session.delete(queuedProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (queuedProcess != null) {
			clearCache(queuedProcess);
		}

		return queuedProcess;
	}

	@Override
	public QueuedProcess updateImpl(QueuedProcess queuedProcess) {
		boolean isNew = queuedProcess.isNew();

		if (!(queuedProcess instanceof QueuedProcessModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(queuedProcess.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(queuedProcess);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in queuedProcess proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom QueuedProcess implementation " +
				queuedProcess.getClass());
		}

		QueuedProcessModelImpl queuedProcessModelImpl = (QueuedProcessModelImpl)queuedProcess;

		if (Validator.isNull(queuedProcess.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			queuedProcess.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (queuedProcess.isNew()) {
				session.save(queuedProcess);

				queuedProcess.setNew(false);
			}
			else {
				queuedProcess = (QueuedProcess)session.merge(queuedProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!QueuedProcessModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { queuedProcessModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					queuedProcessModelImpl.getUuid(),
					queuedProcessModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					queuedProcessModelImpl.getGroupId(),
					queuedProcessModelImpl.getProcessName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPID,
				args);

			args = new Object[] {
					queuedProcessModelImpl.getGroupId(),
					queuedProcessModelImpl.getProcessName(),
					queuedProcessModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPIDSTATUS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((queuedProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						queuedProcessModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { queuedProcessModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((queuedProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						queuedProcessModelImpl.getOriginalUuid(),
						queuedProcessModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						queuedProcessModelImpl.getUuid(),
						queuedProcessModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((queuedProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						queuedProcessModelImpl.getOriginalGroupId(),
						queuedProcessModelImpl.getOriginalProcessName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPID,
					args);

				args = new Object[] {
						queuedProcessModelImpl.getGroupId(),
						queuedProcessModelImpl.getProcessName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPID,
					args);
			}

			if ((queuedProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						queuedProcessModelImpl.getOriginalGroupId(),
						queuedProcessModelImpl.getOriginalProcessName(),
						queuedProcessModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPIDSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS,
					args);

				args = new Object[] {
						queuedProcessModelImpl.getGroupId(),
						queuedProcessModelImpl.getProcessName(),
						queuedProcessModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSNAMEGROUPIDSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSNAMEGROUPIDSTATUS,
					args);
			}
		}

		entityCache.putResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
			QueuedProcessImpl.class, queuedProcess.getPrimaryKey(),
			queuedProcess, false);

		clearUniqueFindersCache(queuedProcessModelImpl, false);
		cacheUniqueFindersCache(queuedProcessModelImpl);

		queuedProcess.resetOriginalValues();

		return queuedProcess;
	}

	/**
	 * Returns the queued process with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the queued process
	 * @return the queued process
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess findByPrimaryKey(Serializable primaryKey)
		throws NoSuchQueuedProcessException {
		QueuedProcess queuedProcess = fetchByPrimaryKey(primaryKey);

		if (queuedProcess == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchQueuedProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return queuedProcess;
	}

	/**
	 * Returns the queued process with the primary key or throws a {@link NoSuchQueuedProcessException} if it could not be found.
	 *
	 * @param queuedProcessId the primary key of the queued process
	 * @return the queued process
	 * @throws NoSuchQueuedProcessException if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess findByPrimaryKey(long queuedProcessId)
		throws NoSuchQueuedProcessException {
		return findByPrimaryKey((Serializable)queuedProcessId);
	}

	/**
	 * Returns the queued process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the queued process
	 * @return the queued process, or <code>null</code> if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
				QueuedProcessImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		QueuedProcess queuedProcess = (QueuedProcess)serializable;

		if (queuedProcess == null) {
			Session session = null;

			try {
				session = openSession();

				queuedProcess = (QueuedProcess)session.get(QueuedProcessImpl.class,
						primaryKey);

				if (queuedProcess != null) {
					cacheResult(queuedProcess);
				}
				else {
					entityCache.putResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
						QueuedProcessImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
					QueuedProcessImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return queuedProcess;
	}

	/**
	 * Returns the queued process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param queuedProcessId the primary key of the queued process
	 * @return the queued process, or <code>null</code> if a queued process with the primary key could not be found
	 */
	@Override
	public QueuedProcess fetchByPrimaryKey(long queuedProcessId) {
		return fetchByPrimaryKey((Serializable)queuedProcessId);
	}

	@Override
	public Map<Serializable, QueuedProcess> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, QueuedProcess> map = new HashMap<Serializable, QueuedProcess>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			QueuedProcess queuedProcess = fetchByPrimaryKey(primaryKey);

			if (queuedProcess != null) {
				map.put(primaryKey, queuedProcess);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
					QueuedProcessImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (QueuedProcess)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_QUEUEDPROCESS_WHERE_PKS_IN);

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

			for (QueuedProcess queuedProcess : (List<QueuedProcess>)q.list()) {
				map.put(queuedProcess.getPrimaryKeyObj(), queuedProcess);

				cacheResult(queuedProcess);

				uncachedPrimaryKeys.remove(queuedProcess.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(QueuedProcessModelImpl.ENTITY_CACHE_ENABLED,
					QueuedProcessImpl.class, primaryKey, nullModel);
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
	 * Returns all the queued processes.
	 *
	 * @return the queued processes
	 */
	@Override
	public List<QueuedProcess> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the queued processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @return the range of queued processes
	 */
	@Override
	public List<QueuedProcess> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the queued processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of queued processes
	 */
	@Override
	public List<QueuedProcess> findAll(int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the queued processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link QueuedProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued processes
	 * @param end the upper bound of the range of queued processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of queued processes
	 */
	@Override
	public List<QueuedProcess> findAll(int start, int end,
		OrderByComparator<QueuedProcess> orderByComparator,
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

		List<QueuedProcess> list = null;

		if (retrieveFromCache) {
			list = (List<QueuedProcess>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_QUEUEDPROCESS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_QUEUEDPROCESS;

				if (pagination) {
					sql = sql.concat(QueuedProcessModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<QueuedProcess>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the queued processes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (QueuedProcess queuedProcess : findAll()) {
			remove(queuedProcess);
		}
	}

	/**
	 * Returns the number of queued processes.
	 *
	 * @return the number of queued processes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_QUEUEDPROCESS);

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
		return QueuedProcessModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the queued process persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(QueuedProcessImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_QUEUEDPROCESS = "SELECT queuedProcess FROM QueuedProcess queuedProcess";
	private static final String _SQL_SELECT_QUEUEDPROCESS_WHERE_PKS_IN = "SELECT queuedProcess FROM QueuedProcess queuedProcess WHERE queuedProcessId IN (";
	private static final String _SQL_SELECT_QUEUEDPROCESS_WHERE = "SELECT queuedProcess FROM QueuedProcess queuedProcess WHERE ";
	private static final String _SQL_COUNT_QUEUEDPROCESS = "SELECT COUNT(queuedProcess) FROM QueuedProcess queuedProcess";
	private static final String _SQL_COUNT_QUEUEDPROCESS_WHERE = "SELECT COUNT(queuedProcess) FROM QueuedProcess queuedProcess WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "queuedProcess.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No QueuedProcess exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No QueuedProcess exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(QueuedProcessPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}
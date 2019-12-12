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

import es.aragon.base.migration.exception.NoSuchContentException;
import es.aragon.base.migration.model.Content;
import es.aragon.base.migration.model.impl.ContentImpl;
import es.aragon.base.migration.model.impl.ContentModelImpl;
import es.aragon.base.migration.service.persistence.ContentPersistence;

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
 * The persistence implementation for the content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentPersistence
 * @see es.aragon.base.migration.service.persistence.ContentUtil
 * @generated
 */
@ProviderType
public class ContentPersistenceImpl extends BasePersistenceImpl<Content>
	implements ContentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContentUtil} to access the content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ContentModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the contents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching contents
	 */
	@Override
	public List<Content> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents
	 */
	@Override
	public List<Content> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByUuid(String uuid, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByUuid(String uuid, int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
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

		List<Content> list = null;

		if (retrieveFromCache) {
			list = (List<Content>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Content content : list) {
					if (!Objects.equals(uuid, content.getUuid())) {
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

			query.append(_SQL_SELECT_CONTENT_WHERE);

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
				query.append(ContentModelImpl.ORDER_BY_JPQL);
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
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByUuid_First(String uuid,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByUuid_First(uuid, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the first content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByUuid_First(String uuid,
		OrderByComparator<Content> orderByComparator) {
		List<Content> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByUuid_Last(String uuid,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByUuid_Last(uuid, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the last content in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByUuid_Last(String uuid,
		OrderByComparator<Content> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Content> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contents before and after the current content in the ordered set where uuid = &#63;.
	 *
	 * @param contentId the primary key of the current content
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content[] findByUuid_PrevAndNext(long contentId, String uuid,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = getByUuid_PrevAndNext(session, content, uuid,
					orderByComparator, true);

			array[1] = content;

			array[2] = getByUuid_PrevAndNext(session, content, uuid,
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

	protected Content getByUuid_PrevAndNext(Session session, Content content,
		String uuid, OrderByComparator<Content> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENT_WHERE);

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
			query.append(ContentModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contents where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Content content : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching contents
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "content.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "content.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(content.uuid IS NULL OR content.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSIGNEDUSERID =
		new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssignedUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEDUSERID =
		new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssignedUserId",
			new String[] { Long.class.getName() },
			ContentModelImpl.ASSIGNEDUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSIGNEDUSERID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssignedUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the contents where assignedUserId = &#63;.
	 *
	 * @param assignedUserId the assigned user ID
	 * @return the matching contents
	 */
	@Override
	public List<Content> findByAssignedUserId(long assignedUserId) {
		return findByAssignedUserId(assignedUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents where assignedUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assignedUserId the assigned user ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents
	 */
	@Override
	public List<Content> findByAssignedUserId(long assignedUserId, int start,
		int end) {
		return findByAssignedUserId(assignedUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents where assignedUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assignedUserId the assigned user ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByAssignedUserId(long assignedUserId, int start,
		int end, OrderByComparator<Content> orderByComparator) {
		return findByAssignedUserId(assignedUserId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contents where assignedUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assignedUserId the assigned user ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByAssignedUserId(long assignedUserId, int start,
		int end, OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEDUSERID;
			finderArgs = new Object[] { assignedUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSIGNEDUSERID;
			finderArgs = new Object[] {
					assignedUserId,
					
					start, end, orderByComparator
				};
		}

		List<Content> list = null;

		if (retrieveFromCache) {
			list = (List<Content>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Content content : list) {
					if ((assignedUserId != content.getAssignedUserId())) {
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

			query.append(_SQL_SELECT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEDUSERID_ASSIGNEDUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assignedUserId);

				if (!pagination) {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first content in the ordered set where assignedUserId = &#63;.
	 *
	 * @param assignedUserId the assigned user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByAssignedUserId_First(long assignedUserId,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByAssignedUserId_First(assignedUserId,
				orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assignedUserId=");
		msg.append(assignedUserId);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the first content in the ordered set where assignedUserId = &#63;.
	 *
	 * @param assignedUserId the assigned user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByAssignedUserId_First(long assignedUserId,
		OrderByComparator<Content> orderByComparator) {
		List<Content> list = findByAssignedUserId(assignedUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content in the ordered set where assignedUserId = &#63;.
	 *
	 * @param assignedUserId the assigned user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByAssignedUserId_Last(long assignedUserId,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByAssignedUserId_Last(assignedUserId,
				orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assignedUserId=");
		msg.append(assignedUserId);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the last content in the ordered set where assignedUserId = &#63;.
	 *
	 * @param assignedUserId the assigned user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByAssignedUserId_Last(long assignedUserId,
		OrderByComparator<Content> orderByComparator) {
		int count = countByAssignedUserId(assignedUserId);

		if (count == 0) {
			return null;
		}

		List<Content> list = findByAssignedUserId(assignedUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contents before and after the current content in the ordered set where assignedUserId = &#63;.
	 *
	 * @param contentId the primary key of the current content
	 * @param assignedUserId the assigned user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content[] findByAssignedUserId_PrevAndNext(long contentId,
		long assignedUserId, OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = getByAssignedUserId_PrevAndNext(session, content,
					assignedUserId, orderByComparator, true);

			array[1] = content;

			array[2] = getByAssignedUserId_PrevAndNext(session, content,
					assignedUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Content getByAssignedUserId_PrevAndNext(Session session,
		Content content, long assignedUserId,
		OrderByComparator<Content> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENT_WHERE);

		query.append(_FINDER_COLUMN_ASSIGNEDUSERID_ASSIGNEDUSERID_2);

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
			query.append(ContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assignedUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contents where assignedUserId = &#63; from the database.
	 *
	 * @param assignedUserId the assigned user ID
	 */
	@Override
	public void removeByAssignedUserId(long assignedUserId) {
		for (Content content : findByAssignedUserId(assignedUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents where assignedUserId = &#63;.
	 *
	 * @param assignedUserId the assigned user ID
	 * @return the number of matching contents
	 */
	@Override
	public int countByAssignedUserId(long assignedUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSIGNEDUSERID;

		Object[] finderArgs = new Object[] { assignedUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEDUSERID_ASSIGNEDUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assignedUserId);

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

	private static final String _FINDER_COLUMN_ASSIGNEDUSERID_ASSIGNEDUSERID_2 = "content.assignedUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LASTMODIFIEDUSERID =
		new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLastModifiedUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTMODIFIEDUSERID =
		new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLastModifiedUserId", new String[] { Long.class.getName() },
			ContentModelImpl.LASTMODIFIEDUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LASTMODIFIEDUSERID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLastModifiedUserId", new String[] { Long.class.getName() });

	/**
	 * Returns all the contents where lastModifiedUserId = &#63;.
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @return the matching contents
	 */
	@Override
	public List<Content> findByLastModifiedUserId(long lastModifiedUserId) {
		return findByLastModifiedUserId(lastModifiedUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents where lastModifiedUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents
	 */
	@Override
	public List<Content> findByLastModifiedUserId(long lastModifiedUserId,
		int start, int end) {
		return findByLastModifiedUserId(lastModifiedUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents where lastModifiedUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByLastModifiedUserId(long lastModifiedUserId,
		int start, int end, OrderByComparator<Content> orderByComparator) {
		return findByLastModifiedUserId(lastModifiedUserId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contents where lastModifiedUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByLastModifiedUserId(long lastModifiedUserId,
		int start, int end, OrderByComparator<Content> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTMODIFIEDUSERID;
			finderArgs = new Object[] { lastModifiedUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LASTMODIFIEDUSERID;
			finderArgs = new Object[] {
					lastModifiedUserId,
					
					start, end, orderByComparator
				};
		}

		List<Content> list = null;

		if (retrieveFromCache) {
			list = (List<Content>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Content content : list) {
					if ((lastModifiedUserId != content.getLastModifiedUserId())) {
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

			query.append(_SQL_SELECT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_LASTMODIFIEDUSERID_LASTMODIFIEDUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lastModifiedUserId);

				if (!pagination) {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first content in the ordered set where lastModifiedUserId = &#63;.
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByLastModifiedUserId_First(long lastModifiedUserId,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByLastModifiedUserId_First(lastModifiedUserId,
				orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lastModifiedUserId=");
		msg.append(lastModifiedUserId);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the first content in the ordered set where lastModifiedUserId = &#63;.
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByLastModifiedUserId_First(long lastModifiedUserId,
		OrderByComparator<Content> orderByComparator) {
		List<Content> list = findByLastModifiedUserId(lastModifiedUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content in the ordered set where lastModifiedUserId = &#63;.
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByLastModifiedUserId_Last(long lastModifiedUserId,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByLastModifiedUserId_Last(lastModifiedUserId,
				orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("lastModifiedUserId=");
		msg.append(lastModifiedUserId);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the last content in the ordered set where lastModifiedUserId = &#63;.
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByLastModifiedUserId_Last(long lastModifiedUserId,
		OrderByComparator<Content> orderByComparator) {
		int count = countByLastModifiedUserId(lastModifiedUserId);

		if (count == 0) {
			return null;
		}

		List<Content> list = findByLastModifiedUserId(lastModifiedUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contents before and after the current content in the ordered set where lastModifiedUserId = &#63;.
	 *
	 * @param contentId the primary key of the current content
	 * @param lastModifiedUserId the last modified user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content[] findByLastModifiedUserId_PrevAndNext(long contentId,
		long lastModifiedUserId, OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = getByLastModifiedUserId_PrevAndNext(session, content,
					lastModifiedUserId, orderByComparator, true);

			array[1] = content;

			array[2] = getByLastModifiedUserId_PrevAndNext(session, content,
					lastModifiedUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Content getByLastModifiedUserId_PrevAndNext(Session session,
		Content content, long lastModifiedUserId,
		OrderByComparator<Content> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENT_WHERE);

		query.append(_FINDER_COLUMN_LASTMODIFIEDUSERID_LASTMODIFIEDUSERID_2);

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
			query.append(ContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(lastModifiedUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contents where lastModifiedUserId = &#63; from the database.
	 *
	 * @param lastModifiedUserId the last modified user ID
	 */
	@Override
	public void removeByLastModifiedUserId(long lastModifiedUserId) {
		for (Content content : findByLastModifiedUserId(lastModifiedUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents where lastModifiedUserId = &#63;.
	 *
	 * @param lastModifiedUserId the last modified user ID
	 * @return the number of matching contents
	 */
	@Override
	public int countByLastModifiedUserId(long lastModifiedUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LASTMODIFIEDUSERID;

		Object[] finderArgs = new Object[] { lastModifiedUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_LASTMODIFIEDUSERID_LASTMODIFIEDUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(lastModifiedUserId);

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

	private static final String _FINDER_COLUMN_LASTMODIFIEDUSERID_LASTMODIFIEDUSERID_2 =
		"content.lastModifiedUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AREAID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAreaId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AREAID =
		new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAreaId",
			new String[] { Long.class.getName() },
			ContentModelImpl.AREAID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AREAID = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAreaId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the contents where areaId = &#63;.
	 *
	 * @param areaId the area ID
	 * @return the matching contents
	 */
	@Override
	public List<Content> findByAreaId(long areaId) {
		return findByAreaId(areaId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents where areaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param areaId the area ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents
	 */
	@Override
	public List<Content> findByAreaId(long areaId, int start, int end) {
		return findByAreaId(areaId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents where areaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param areaId the area ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByAreaId(long areaId, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return findByAreaId(areaId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contents where areaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param areaId the area ID
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByAreaId(long areaId, int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AREAID;
			finderArgs = new Object[] { areaId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AREAID;
			finderArgs = new Object[] { areaId, start, end, orderByComparator };
		}

		List<Content> list = null;

		if (retrieveFromCache) {
			list = (List<Content>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Content content : list) {
					if ((areaId != content.getAreaId())) {
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

			query.append(_SQL_SELECT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_AREAID_AREAID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(areaId);

				if (!pagination) {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first content in the ordered set where areaId = &#63;.
	 *
	 * @param areaId the area ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByAreaId_First(long areaId,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByAreaId_First(areaId, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("areaId=");
		msg.append(areaId);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the first content in the ordered set where areaId = &#63;.
	 *
	 * @param areaId the area ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByAreaId_First(long areaId,
		OrderByComparator<Content> orderByComparator) {
		List<Content> list = findByAreaId(areaId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content in the ordered set where areaId = &#63;.
	 *
	 * @param areaId the area ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByAreaId_Last(long areaId,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByAreaId_Last(areaId, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("areaId=");
		msg.append(areaId);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the last content in the ordered set where areaId = &#63;.
	 *
	 * @param areaId the area ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByAreaId_Last(long areaId,
		OrderByComparator<Content> orderByComparator) {
		int count = countByAreaId(areaId);

		if (count == 0) {
			return null;
		}

		List<Content> list = findByAreaId(areaId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contents before and after the current content in the ordered set where areaId = &#63;.
	 *
	 * @param contentId the primary key of the current content
	 * @param areaId the area ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content[] findByAreaId_PrevAndNext(long contentId, long areaId,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = getByAreaId_PrevAndNext(session, content, areaId,
					orderByComparator, true);

			array[1] = content;

			array[2] = getByAreaId_PrevAndNext(session, content, areaId,
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

	protected Content getByAreaId_PrevAndNext(Session session, Content content,
		long areaId, OrderByComparator<Content> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENT_WHERE);

		query.append(_FINDER_COLUMN_AREAID_AREAID_2);

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
			query.append(ContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(areaId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contents where areaId = &#63; from the database.
	 *
	 * @param areaId the area ID
	 */
	@Override
	public void removeByAreaId(long areaId) {
		for (Content content : findByAreaId(areaId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents where areaId = &#63;.
	 *
	 * @param areaId the area ID
	 * @return the number of matching contents
	 */
	@Override
	public int countByAreaId(long areaId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AREAID;

		Object[] finderArgs = new Object[] { areaId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENT_WHERE);

			query.append(_FINDER_COLUMN_AREAID_AREAID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(areaId);

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

	private static final String _FINDER_COLUMN_AREAID_AREAID_2 = "content.areaId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_URL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUrl",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_URL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, ContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUrl",
			new String[] { String.class.getName() },
			ContentModelImpl.URL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_URL = new FinderPath(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUrl",
			new String[] { String.class.getName() });

	/**
	 * Returns all the contents where url = &#63;.
	 *
	 * @param url the url
	 * @return the matching contents
	 */
	@Override
	public List<Content> findByUrl(String url) {
		return findByUrl(url, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents where url = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param url the url
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of matching contents
	 */
	@Override
	public List<Content> findByUrl(String url, int start, int end) {
		return findByUrl(url, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents where url = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param url the url
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByUrl(String url, int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return findByUrl(url, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contents where url = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param url the url
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contents
	 */
	@Override
	public List<Content> findByUrl(String url, int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_URL;
			finderArgs = new Object[] { url };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_URL;
			finderArgs = new Object[] { url, start, end, orderByComparator };
		}

		List<Content> list = null;

		if (retrieveFromCache) {
			list = (List<Content>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Content content : list) {
					if (!Objects.equals(url, content.getUrl())) {
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

			query.append(_SQL_SELECT_CONTENT_WHERE);

			boolean bindUrl = false;

			if (url == null) {
				query.append(_FINDER_COLUMN_URL_URL_1);
			}
			else if (url.equals("")) {
				query.append(_FINDER_COLUMN_URL_URL_3);
			}
			else {
				bindUrl = true;

				query.append(_FINDER_COLUMN_URL_URL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUrl) {
					qPos.add(url);
				}

				if (!pagination) {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first content in the ordered set where url = &#63;.
	 *
	 * @param url the url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByUrl_First(String url,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByUrl_First(url, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("url=");
		msg.append(url);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the first content in the ordered set where url = &#63;.
	 *
	 * @param url the url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByUrl_First(String url,
		OrderByComparator<Content> orderByComparator) {
		List<Content> list = findByUrl(url, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last content in the ordered set where url = &#63;.
	 *
	 * @param url the url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content
	 * @throws NoSuchContentException if a matching content could not be found
	 */
	@Override
	public Content findByUrl_Last(String url,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = fetchByUrl_Last(url, orderByComparator);

		if (content != null) {
			return content;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("url=");
		msg.append(url);

		msg.append("}");

		throw new NoSuchContentException(msg.toString());
	}

	/**
	 * Returns the last content in the ordered set where url = &#63;.
	 *
	 * @param url the url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching content, or <code>null</code> if a matching content could not be found
	 */
	@Override
	public Content fetchByUrl_Last(String url,
		OrderByComparator<Content> orderByComparator) {
		int count = countByUrl(url);

		if (count == 0) {
			return null;
		}

		List<Content> list = findByUrl(url, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contents before and after the current content in the ordered set where url = &#63;.
	 *
	 * @param contentId the primary key of the current content
	 * @param url the url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next content
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content[] findByUrl_PrevAndNext(long contentId, String url,
		OrderByComparator<Content> orderByComparator)
		throws NoSuchContentException {
		Content content = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			Content[] array = new ContentImpl[3];

			array[0] = getByUrl_PrevAndNext(session, content, url,
					orderByComparator, true);

			array[1] = content;

			array[2] = getByUrl_PrevAndNext(session, content, url,
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

	protected Content getByUrl_PrevAndNext(Session session, Content content,
		String url, OrderByComparator<Content> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CONTENT_WHERE);

		boolean bindUrl = false;

		if (url == null) {
			query.append(_FINDER_COLUMN_URL_URL_1);
		}
		else if (url.equals("")) {
			query.append(_FINDER_COLUMN_URL_URL_3);
		}
		else {
			bindUrl = true;

			query.append(_FINDER_COLUMN_URL_URL_2);
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
			query.append(ContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUrl) {
			qPos.add(url);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(content);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Content> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contents where url = &#63; from the database.
	 *
	 * @param url the url
	 */
	@Override
	public void removeByUrl(String url) {
		for (Content content : findByUrl(url, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents where url = &#63;.
	 *
	 * @param url the url
	 * @return the number of matching contents
	 */
	@Override
	public int countByUrl(String url) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_URL;

		Object[] finderArgs = new Object[] { url };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CONTENT_WHERE);

			boolean bindUrl = false;

			if (url == null) {
				query.append(_FINDER_COLUMN_URL_URL_1);
			}
			else if (url.equals("")) {
				query.append(_FINDER_COLUMN_URL_URL_3);
			}
			else {
				bindUrl = true;

				query.append(_FINDER_COLUMN_URL_URL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUrl) {
					qPos.add(url);
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

	private static final String _FINDER_COLUMN_URL_URL_1 = "content.url IS NULL";
	private static final String _FINDER_COLUMN_URL_URL_2 = "content.url = ?";
	private static final String _FINDER_COLUMN_URL_URL_3 = "(content.url IS NULL OR content.url = '')";

	public ContentPersistenceImpl() {
		setModelClass(Content.class);

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
	 * Caches the content in the entity cache if it is enabled.
	 *
	 * @param content the content
	 */
	@Override
	public void cacheResult(Content content) {
		entityCache.putResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentImpl.class, content.getPrimaryKey(), content);

		content.resetOriginalValues();
	}

	/**
	 * Caches the contents in the entity cache if it is enabled.
	 *
	 * @param contents the contents
	 */
	@Override
	public void cacheResult(List<Content> contents) {
		for (Content content : contents) {
			if (entityCache.getResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
						ContentImpl.class, content.getPrimaryKey()) == null) {
				cacheResult(content);
			}
			else {
				content.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contents.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the content.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Content content) {
		entityCache.removeResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentImpl.class, content.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Content> contents) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Content content : contents) {
			entityCache.removeResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
				ContentImpl.class, content.getPrimaryKey());
		}
	}

	/**
	 * Creates a new content with the primary key. Does not add the content to the database.
	 *
	 * @param contentId the primary key for the new content
	 * @return the new content
	 */
	@Override
	public Content create(long contentId) {
		Content content = new ContentImpl();

		content.setNew(true);
		content.setPrimaryKey(contentId);

		String uuid = PortalUUIDUtil.generate();

		content.setUuid(uuid);

		return content;
	}

	/**
	 * Removes the content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentId the primary key of the content
	 * @return the content that was removed
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content remove(long contentId) throws NoSuchContentException {
		return remove((Serializable)contentId);
	}

	/**
	 * Removes the content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the content
	 * @return the content that was removed
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content remove(Serializable primaryKey)
		throws NoSuchContentException {
		Session session = null;

		try {
			session = openSession();

			Content content = (Content)session.get(ContentImpl.class, primaryKey);

			if (content == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(content);
		}
		catch (NoSuchContentException nsee) {
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
	protected Content removeImpl(Content content) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(content)) {
				content = (Content)session.get(ContentImpl.class,
						content.getPrimaryKeyObj());
			}

			if (content != null) {
				session.delete(content);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (content != null) {
			clearCache(content);
		}

		return content;
	}

	@Override
	public Content updateImpl(Content content) {
		boolean isNew = content.isNew();

		if (!(content instanceof ContentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(content.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(content);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in content proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Content implementation " +
				content.getClass());
		}

		ContentModelImpl contentModelImpl = (ContentModelImpl)content;

		if (Validator.isNull(content.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			content.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (content.isNew()) {
				session.save(content);

				content.setNew(false);
			}
			else {
				content = (Content)session.merge(content);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { contentModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { contentModelImpl.getAssignedUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSIGNEDUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEDUSERID,
				args);

			args = new Object[] { contentModelImpl.getLastModifiedUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LASTMODIFIEDUSERID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTMODIFIEDUSERID,
				args);

			args = new Object[] { contentModelImpl.getAreaId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AREAID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AREAID,
				args);

			args = new Object[] { contentModelImpl.getUrl() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_URL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_URL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { contentModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { contentModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentModelImpl.getOriginalAssignedUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSIGNEDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEDUSERID,
					args);

				args = new Object[] { contentModelImpl.getAssignedUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSIGNEDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEDUSERID,
					args);
			}

			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTMODIFIEDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentModelImpl.getOriginalLastModifiedUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LASTMODIFIEDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTMODIFIEDUSERID,
					args);

				args = new Object[] { contentModelImpl.getLastModifiedUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LASTMODIFIEDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LASTMODIFIEDUSERID,
					args);
			}

			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AREAID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contentModelImpl.getOriginalAreaId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AREAID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AREAID,
					args);

				args = new Object[] { contentModelImpl.getAreaId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AREAID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AREAID,
					args);
			}

			if ((contentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_URL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { contentModelImpl.getOriginalUrl() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_URL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_URL,
					args);

				args = new Object[] { contentModelImpl.getUrl() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_URL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_URL,
					args);
			}
		}

		entityCache.putResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
			ContentImpl.class, content.getPrimaryKey(), content, false);

		content.resetOriginalValues();

		return content;
	}

	/**
	 * Returns the content with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the content
	 * @return the content
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContentException {
		Content content = fetchByPrimaryKey(primaryKey);

		if (content == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return content;
	}

	/**
	 * Returns the content with the primary key or throws a {@link NoSuchContentException} if it could not be found.
	 *
	 * @param contentId the primary key of the content
	 * @return the content
	 * @throws NoSuchContentException if a content with the primary key could not be found
	 */
	@Override
	public Content findByPrimaryKey(long contentId)
		throws NoSuchContentException {
		return findByPrimaryKey((Serializable)contentId);
	}

	/**
	 * Returns the content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the content
	 * @return the content, or <code>null</code> if a content with the primary key could not be found
	 */
	@Override
	public Content fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
				ContentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Content content = (Content)serializable;

		if (content == null) {
			Session session = null;

			try {
				session = openSession();

				content = (Content)session.get(ContentImpl.class, primaryKey);

				if (content != null) {
					cacheResult(content);
				}
				else {
					entityCache.putResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
						ContentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
					ContentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return content;
	}

	/**
	 * Returns the content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentId the primary key of the content
	 * @return the content, or <code>null</code> if a content with the primary key could not be found
	 */
	@Override
	public Content fetchByPrimaryKey(long contentId) {
		return fetchByPrimaryKey((Serializable)contentId);
	}

	@Override
	public Map<Serializable, Content> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Content> map = new HashMap<Serializable, Content>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Content content = fetchByPrimaryKey(primaryKey);

			if (content != null) {
				map.put(primaryKey, content);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
					ContentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Content)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTENT_WHERE_PKS_IN);

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

			for (Content content : (List<Content>)q.list()) {
				map.put(content.getPrimaryKeyObj(), content);

				cacheResult(content);

				uncachedPrimaryKeys.remove(content.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContentModelImpl.ENTITY_CACHE_ENABLED,
					ContentImpl.class, primaryKey, nullModel);
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
	 * Returns all the contents.
	 *
	 * @return the contents
	 */
	@Override
	public List<Content> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @return the range of contents
	 */
	@Override
	public List<Content> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contents
	 */
	@Override
	public List<Content> findAll(int start, int end,
		OrderByComparator<Content> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contents
	 * @param end the upper bound of the range of contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of contents
	 */
	@Override
	public List<Content> findAll(int start, int end,
		OrderByComparator<Content> orderByComparator, boolean retrieveFromCache) {
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

		List<Content> list = null;

		if (retrieveFromCache) {
			list = (List<Content>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTENT;

				if (pagination) {
					sql = sql.concat(ContentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Content>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Content content : findAll()) {
			remove(content);
		}
	}

	/**
	 * Returns the number of contents.
	 *
	 * @return the number of contents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTENT);

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
		return ContentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the content persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONTENT = "SELECT content FROM Content content";
	private static final String _SQL_SELECT_CONTENT_WHERE_PKS_IN = "SELECT content FROM Content content WHERE contentId IN (";
	private static final String _SQL_SELECT_CONTENT_WHERE = "SELECT content FROM Content content WHERE ";
	private static final String _SQL_COUNT_CONTENT = "SELECT COUNT(content) FROM Content content";
	private static final String _SQL_COUNT_CONTENT_WHERE = "SELECT COUNT(content) FROM Content content WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "content.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Content exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Content exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}
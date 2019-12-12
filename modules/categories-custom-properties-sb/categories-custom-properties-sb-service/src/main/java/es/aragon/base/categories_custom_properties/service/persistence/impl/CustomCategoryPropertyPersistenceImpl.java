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

package es.aragon.base.categories_custom_properties.service.persistence.impl;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
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

import es.aragon.base.categories_custom_properties.exception.NoSuchCustomCategoryPropertyException;
import es.aragon.base.categories_custom_properties.model.CustomCategoryProperty;
import es.aragon.base.categories_custom_properties.model.impl.CustomCategoryPropertyImpl;
import es.aragon.base.categories_custom_properties.model.impl.CustomCategoryPropertyModelImpl;
import es.aragon.base.categories_custom_properties.service.persistence.CustomCategoryPropertyPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the custom category property service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CustomCategoryPropertyPersistence
 * @see es.aragon.base.categories_custom_properties.service.persistence.CustomCategoryPropertyUtil
 * @generated
 */
@ProviderType
public class CustomCategoryPropertyPersistenceImpl extends BasePersistenceImpl<CustomCategoryProperty>
	implements CustomCategoryPropertyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CustomCategoryPropertyUtil} to access the custom category property persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CustomCategoryPropertyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CustomCategoryPropertyModelImpl.UUID_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the custom category properties where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom category properties where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @return the range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the custom category properties where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid(String uuid, int start,
		int end, OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom category properties where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid(String uuid, int start,
		int end, OrderByComparator<CustomCategoryProperty> orderByComparator,
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

		List<CustomCategoryProperty> list = null;

		if (retrieveFromCache) {
			list = (List<CustomCategoryProperty>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CustomCategoryProperty customCategoryProperty : list) {
					if (!Objects.equals(uuid, customCategoryProperty.getUuid())) {
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

			query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

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
				query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
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
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
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
	 * Returns the first custom category property in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByUuid_First(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByUuid_First(uuid,
				orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the first custom category property in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByUuid_First(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		List<CustomCategoryProperty> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last custom category property in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByUuid_Last(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByUuid_Last(uuid,
				orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the last custom category property in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByUuid_Last(String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CustomCategoryProperty> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the custom category properties before and after the current custom category property in the ordered set where uuid = &#63;.
	 *
	 * @param CustomCategoryPropertyId the primary key of the current custom category property
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty[] findByUuid_PrevAndNext(
		long CustomCategoryPropertyId, String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = findByPrimaryKey(CustomCategoryPropertyId);

		Session session = null;

		try {
			session = openSession();

			CustomCategoryProperty[] array = new CustomCategoryPropertyImpl[3];

			array[0] = getByUuid_PrevAndNext(session, customCategoryProperty,
					uuid, orderByComparator, true);

			array[1] = customCategoryProperty;

			array[2] = getByUuid_PrevAndNext(session, customCategoryProperty,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CustomCategoryProperty getByUuid_PrevAndNext(Session session,
		CustomCategoryProperty customCategoryProperty, String uuid,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
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

		query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

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
			query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(customCategoryProperty);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CustomCategoryProperty> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the custom category properties where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CustomCategoryProperty customCategoryProperty : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(customCategoryProperty);
		}
	}

	/**
	 * Returns the number of custom category properties where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching custom category properties
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CUSTOMCATEGORYPROPERTY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "customCategoryProperty.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "customCategoryProperty.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(customCategoryProperty.uuid IS NULL OR customCategoryProperty.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CustomCategoryPropertyModelImpl.UUID_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the custom category property where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByUUID_G(String uuid, long groupId)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByUUID_G(uuid,
				groupId);

		if (customCategoryProperty == null) {
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

			throw new NoSuchCustomCategoryPropertyException(msg.toString());
		}

		return customCategoryProperty;
	}

	/**
	 * Returns the custom category property where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the custom category property where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CustomCategoryProperty) {
			CustomCategoryProperty customCategoryProperty = (CustomCategoryProperty)result;

			if (!Objects.equals(uuid, customCategoryProperty.getUuid()) ||
					(groupId != customCategoryProperty.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

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

				List<CustomCategoryProperty> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CustomCategoryProperty customCategoryProperty = list.get(0);

					result = customCategoryProperty;

					cacheResult(customCategoryProperty);
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
			return (CustomCategoryProperty)result;
		}
	}

	/**
	 * Removes the custom category property where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the custom category property that was removed
	 */
	@Override
	public CustomCategoryProperty removeByUUID_G(String uuid, long groupId)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = findByUUID_G(uuid,
				groupId);

		return remove(customCategoryProperty);
	}

	/**
	 * Returns the number of custom category properties where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching custom category properties
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CUSTOMCATEGORYPROPERTY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "customCategoryProperty.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "customCategoryProperty.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(customCategoryProperty.uuid IS NULL OR customCategoryProperty.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "customCategoryProperty.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CustomCategoryPropertyModelImpl.UUID_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.COMPANYID_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the custom category properties where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom category properties where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @return the range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the custom category properties where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom category properties where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
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

		List<CustomCategoryProperty> list = null;

		if (retrieveFromCache) {
			list = (List<CustomCategoryProperty>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CustomCategoryProperty customCategoryProperty : list) {
					if (!Objects.equals(uuid, customCategoryProperty.getUuid()) ||
							(companyId != customCategoryProperty.getCompanyId())) {
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

			query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

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
				query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
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
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
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
	 * Returns the first custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the first custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		List<CustomCategoryProperty> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the last custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CustomCategoryProperty> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the custom category properties before and after the current custom category property in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CustomCategoryPropertyId the primary key of the current custom category property
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty[] findByUuid_C_PrevAndNext(
		long CustomCategoryPropertyId, String uuid, long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = findByPrimaryKey(CustomCategoryPropertyId);

		Session session = null;

		try {
			session = openSession();

			CustomCategoryProperty[] array = new CustomCategoryPropertyImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, customCategoryProperty,
					uuid, companyId, orderByComparator, true);

			array[1] = customCategoryProperty;

			array[2] = getByUuid_C_PrevAndNext(session, customCategoryProperty,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CustomCategoryProperty getByUuid_C_PrevAndNext(Session session,
		CustomCategoryProperty customCategoryProperty, String uuid,
		long companyId,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
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

		query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

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
			query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(customCategoryProperty);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CustomCategoryProperty> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the custom category properties where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CustomCategoryProperty customCategoryProperty : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(customCategoryProperty);
		}
	}

	/**
	 * Returns the number of custom category properties where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching custom category properties
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CUSTOMCATEGORYPROPERTY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "customCategoryProperty.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "customCategoryProperty.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(customCategoryProperty.uuid IS NULL OR customCategoryProperty.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "customCategoryProperty.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORYID =
		new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCategoryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYID =
		new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCategoryId",
			new String[] { Long.class.getName() },
			CustomCategoryPropertyModelImpl.CATEGORYID_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYID = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCategoryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the custom category properties where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByCategoryId(long categoryId) {
		return findByCategoryId(categoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom category properties where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @return the range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByCategoryId(long categoryId,
		int start, int end) {
		return findByCategoryId(categoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the custom category properties where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByCategoryId(long categoryId,
		int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return findByCategoryId(categoryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom category properties where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByCategoryId(long categoryId,
		int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYID;
			finderArgs = new Object[] { categoryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORYID;
			finderArgs = new Object[] { categoryId, start, end, orderByComparator };
		}

		List<CustomCategoryProperty> list = null;

		if (retrieveFromCache) {
			list = (List<CustomCategoryProperty>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CustomCategoryProperty customCategoryProperty : list) {
					if ((categoryId != customCategoryProperty.getCategoryId())) {
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

			query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				if (!pagination) {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
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
	 * Returns the first custom category property in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByCategoryId_First(long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByCategoryId_First(categoryId,
				orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("categoryId=");
		msg.append(categoryId);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the first custom category property in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByCategoryId_First(long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		List<CustomCategoryProperty> list = findByCategoryId(categoryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last custom category property in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByCategoryId_Last(long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByCategoryId_Last(categoryId,
				orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("categoryId=");
		msg.append(categoryId);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the last custom category property in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByCategoryId_Last(long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		int count = countByCategoryId(categoryId);

		if (count == 0) {
			return null;
		}

		List<CustomCategoryProperty> list = findByCategoryId(categoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the custom category properties before and after the current custom category property in the ordered set where categoryId = &#63;.
	 *
	 * @param CustomCategoryPropertyId the primary key of the current custom category property
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty[] findByCategoryId_PrevAndNext(
		long CustomCategoryPropertyId, long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = findByPrimaryKey(CustomCategoryPropertyId);

		Session session = null;

		try {
			session = openSession();

			CustomCategoryProperty[] array = new CustomCategoryPropertyImpl[3];

			array[0] = getByCategoryId_PrevAndNext(session,
					customCategoryProperty, categoryId, orderByComparator, true);

			array[1] = customCategoryProperty;

			array[2] = getByCategoryId_PrevAndNext(session,
					customCategoryProperty, categoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CustomCategoryProperty getByCategoryId_PrevAndNext(
		Session session, CustomCategoryProperty customCategoryProperty,
		long categoryId,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
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

		query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

		query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

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
			query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(categoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(customCategoryProperty);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CustomCategoryProperty> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the custom category properties where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 */
	@Override
	public void removeByCategoryId(long categoryId) {
		for (CustomCategoryProperty customCategoryProperty : findByCategoryId(
				categoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(customCategoryProperty);
		}
	}

	/**
	 * Returns the number of custom category properties where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching custom category properties
	 */
	@Override
	public int countByCategoryId(long categoryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CATEGORYID;

		Object[] finderArgs = new Object[] { categoryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CUSTOMCATEGORYPROPERTY_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

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

	private static final String _FINDER_COLUMN_CATEGORYID_CATEGORYID_2 = "customCategoryProperty.categoryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCategoryIdAndKey",
			new String[] { Long.class.getName(), String.class.getName() },
			CustomCategoryPropertyModelImpl.CATEGORYID_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYIDANDKEY = new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCategoryIdAndKey",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the custom category property where categoryId = &#63; and key = &#63; or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	 *
	 * @param categoryId the category ID
	 * @param key the key
	 * @return the matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByCategoryIdAndKey(long categoryId,
		String key) throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByCategoryIdAndKey(categoryId,
				key);

		if (customCategoryProperty == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("categoryId=");
			msg.append(categoryId);

			msg.append(", key=");
			msg.append(key);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCustomCategoryPropertyException(msg.toString());
		}

		return customCategoryProperty;
	}

	/**
	 * Returns the custom category property where categoryId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param categoryId the category ID
	 * @param key the key
	 * @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByCategoryIdAndKey(long categoryId,
		String key) {
		return fetchByCategoryIdAndKey(categoryId, key, true);
	}

	/**
	 * Returns the custom category property where categoryId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param categoryId the category ID
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByCategoryIdAndKey(long categoryId,
		String key, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { categoryId, key };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY,
					finderArgs, this);
		}

		if (result instanceof CustomCategoryProperty) {
			CustomCategoryProperty customCategoryProperty = (CustomCategoryProperty)result;

			if ((categoryId != customCategoryProperty.getCategoryId()) ||
					!Objects.equals(key, customCategoryProperty.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_CATEGORYID_2);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_KEY_1);
			}
			else if (key.equals("")) {
				query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				if (bindKey) {
					qPos.add(key);
				}

				List<CustomCategoryProperty> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY,
						finderArgs, list);
				}
				else {
					CustomCategoryProperty customCategoryProperty = list.get(0);

					result = customCategoryProperty;

					cacheResult(customCategoryProperty);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY,
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
			return (CustomCategoryProperty)result;
		}
	}

	/**
	 * Removes the custom category property where categoryId = &#63; and key = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 * @param key the key
	 * @return the custom category property that was removed
	 */
	@Override
	public CustomCategoryProperty removeByCategoryIdAndKey(long categoryId,
		String key) throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = findByCategoryIdAndKey(categoryId,
				key);

		return remove(customCategoryProperty);
	}

	/**
	 * Returns the number of custom category properties where categoryId = &#63; and key = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param key the key
	 * @return the number of matching custom category properties
	 */
	@Override
	public int countByCategoryIdAndKey(long categoryId, String key) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CATEGORYIDANDKEY;

		Object[] finderArgs = new Object[] { categoryId, key };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CUSTOMCATEGORYPROPERTY_WHERE);

			query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_CATEGORYID_2);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_KEY_1);
			}
			else if (key.equals("")) {
				query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_CATEGORYIDANDKEY_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(categoryId);

				if (bindKey) {
					qPos.add(key);
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

	private static final String _FINDER_COLUMN_CATEGORYIDANDKEY_CATEGORYID_2 = "customCategoryProperty.categoryId = ? AND ";
	private static final String _FINDER_COLUMN_CATEGORYIDANDKEY_KEY_1 = "customCategoryProperty.key IS NULL";
	private static final String _FINDER_COLUMN_CATEGORYIDANDKEY_KEY_2 = "customCategoryProperty.key = ?";
	private static final String _FINDER_COLUMN_CATEGORYIDANDKEY_KEY_3 = "(customCategoryProperty.key IS NULL OR customCategoryProperty.key = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT =
		new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByVocabularyIdAndKeyAndText",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT =
		new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByVocabularyIdAndKeyAndText",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			CustomCategoryPropertyModelImpl.VOCABULARYID_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.KEY_COLUMN_BITMASK |
			CustomCategoryPropertyModelImpl.TEXT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_VOCABULARYIDANDKEYANDTEXT =
		new FinderPath(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByVocabularyIdAndKeyAndText",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @return the matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text) {
		return findByVocabularyIdAndKeyAndText(vocabularyId, key, text,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @return the range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end) {
		return findByVocabularyIdAndKeyAndText(vocabularyId, key, text, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return findByVocabularyIdAndKeyAndText(vocabularyId, key, text, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findByVocabularyIdAndKeyAndText(
		long vocabularyId, String key, String text, int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT;
			finderArgs = new Object[] { vocabularyId, key, text };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT;
			finderArgs = new Object[] {
					vocabularyId, key, text,
					
					start, end, orderByComparator
				};
		}

		List<CustomCategoryProperty> list = null;

		if (retrieveFromCache) {
			list = (List<CustomCategoryProperty>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CustomCategoryProperty customCategoryProperty : list) {
					if ((vocabularyId != customCategoryProperty.getVocabularyId()) ||
							!Objects.equals(key, customCategoryProperty.getKey()) ||
							!Objects.equals(text,
								customCategoryProperty.getText())) {
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

			query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_VOCABULARYID_2);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_1);
			}
			else if (key.equals("")) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_2);
			}

			boolean bindText = false;

			if (text == null) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_1);
			}
			else if (text.equals("")) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_3);
			}
			else {
				bindText = true;

				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				if (bindKey) {
					qPos.add(key);
				}

				if (bindText) {
					qPos.add(text);
				}

				if (!pagination) {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
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
	 * Returns the first custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByVocabularyIdAndKeyAndText_First(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByVocabularyIdAndKeyAndText_First(vocabularyId,
				key, text, orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", key=");
		msg.append(key);

		msg.append(", text=");
		msg.append(text);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the first custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByVocabularyIdAndKeyAndText_First(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		List<CustomCategoryProperty> list = findByVocabularyIdAndKeyAndText(vocabularyId,
				key, text, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty findByVocabularyIdAndKeyAndText_Last(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByVocabularyIdAndKeyAndText_Last(vocabularyId,
				key, text, orderByComparator);

		if (customCategoryProperty != null) {
			return customCategoryProperty;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vocabularyId=");
		msg.append(vocabularyId);

		msg.append(", key=");
		msg.append(key);

		msg.append(", text=");
		msg.append(text);

		msg.append("}");

		throw new NoSuchCustomCategoryPropertyException(msg.toString());
	}

	/**
	 * Returns the last custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching custom category property, or <code>null</code> if a matching custom category property could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByVocabularyIdAndKeyAndText_Last(
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		int count = countByVocabularyIdAndKeyAndText(vocabularyId, key, text);

		if (count == 0) {
			return null;
		}

		List<CustomCategoryProperty> list = findByVocabularyIdAndKeyAndText(vocabularyId,
				key, text, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the custom category properties before and after the current custom category property in the ordered set where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * @param CustomCategoryPropertyId the primary key of the current custom category property
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty[] findByVocabularyIdAndKeyAndText_PrevAndNext(
		long CustomCategoryPropertyId, long vocabularyId, String key,
		String text, OrderByComparator<CustomCategoryProperty> orderByComparator)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = findByPrimaryKey(CustomCategoryPropertyId);

		Session session = null;

		try {
			session = openSession();

			CustomCategoryProperty[] array = new CustomCategoryPropertyImpl[3];

			array[0] = getByVocabularyIdAndKeyAndText_PrevAndNext(session,
					customCategoryProperty, vocabularyId, key, text,
					orderByComparator, true);

			array[1] = customCategoryProperty;

			array[2] = getByVocabularyIdAndKeyAndText_PrevAndNext(session,
					customCategoryProperty, vocabularyId, key, text,
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

	protected CustomCategoryProperty getByVocabularyIdAndKeyAndText_PrevAndNext(
		Session session, CustomCategoryProperty customCategoryProperty,
		long vocabularyId, String key, String text,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE);

		query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_VOCABULARYID_2);

		boolean bindKey = false;

		if (key == null) {
			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_1);
		}
		else if (key.equals("")) {
			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_3);
		}
		else {
			bindKey = true;

			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_2);
		}

		boolean bindText = false;

		if (text == null) {
			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_1);
		}
		else if (text.equals("")) {
			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_3);
		}
		else {
			bindText = true;

			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_2);
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
			query.append(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(vocabularyId);

		if (bindKey) {
			qPos.add(key);
		}

		if (bindText) {
			qPos.add(text);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(customCategoryProperty);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CustomCategoryProperty> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63; from the database.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 */
	@Override
	public void removeByVocabularyIdAndKeyAndText(long vocabularyId,
		String key, String text) {
		for (CustomCategoryProperty customCategoryProperty : findByVocabularyIdAndKeyAndText(
				vocabularyId, key, text, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(customCategoryProperty);
		}
	}

	/**
	 * Returns the number of custom category properties where vocabularyId = &#63; and key = &#63; and text = &#63;.
	 *
	 * @param vocabularyId the vocabulary ID
	 * @param key the key
	 * @param text the text
	 * @return the number of matching custom category properties
	 */
	@Override
	public int countByVocabularyIdAndKeyAndText(long vocabularyId, String key,
		String text) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_VOCABULARYIDANDKEYANDTEXT;

		Object[] finderArgs = new Object[] { vocabularyId, key, text };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CUSTOMCATEGORYPROPERTY_WHERE);

			query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_VOCABULARYID_2);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_1);
			}
			else if (key.equals("")) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_2);
			}

			boolean bindText = false;

			if (text == null) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_1);
			}
			else if (text.equals("")) {
				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_3);
			}
			else {
				bindText = true;

				query.append(_FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(vocabularyId);

				if (bindKey) {
					qPos.add(key);
				}

				if (bindText) {
					qPos.add(text);
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

	private static final String _FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_VOCABULARYID_2 =
		"customCategoryProperty.vocabularyId = ? AND ";
	private static final String _FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_1 = "customCategoryProperty.key IS NULL AND ";
	private static final String _FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_2 = "customCategoryProperty.key = ? AND ";
	private static final String _FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_KEY_3 = "(customCategoryProperty.key IS NULL OR customCategoryProperty.key = '') AND ";
	private static final String _FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_1 = "customCategoryProperty.text IS NULL";
	private static final String _FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_2 = "customCategoryProperty.text = ?";
	private static final String _FINDER_COLUMN_VOCABULARYIDANDKEYANDTEXT_TEXT_3 = "(customCategoryProperty.text IS NULL OR customCategoryProperty.text = '')";

	public CustomCategoryPropertyPersistenceImpl() {
		setModelClass(CustomCategoryProperty.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("key", "key_");
			dbColumnNames.put("text", "text_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the custom category property in the entity cache if it is enabled.
	 *
	 * @param customCategoryProperty the custom category property
	 */
	@Override
	public void cacheResult(CustomCategoryProperty customCategoryProperty) {
		entityCache.putResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			customCategoryProperty.getPrimaryKey(), customCategoryProperty);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				customCategoryProperty.getUuid(),
				customCategoryProperty.getGroupId()
			}, customCategoryProperty);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY,
			new Object[] {
				customCategoryProperty.getCategoryId(),
				customCategoryProperty.getKey()
			}, customCategoryProperty);

		customCategoryProperty.resetOriginalValues();
	}

	/**
	 * Caches the custom category properties in the entity cache if it is enabled.
	 *
	 * @param customCategoryProperties the custom category properties
	 */
	@Override
	public void cacheResult(
		List<CustomCategoryProperty> customCategoryProperties) {
		for (CustomCategoryProperty customCategoryProperty : customCategoryProperties) {
			if (entityCache.getResult(
						CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
						CustomCategoryPropertyImpl.class,
						customCategoryProperty.getPrimaryKey()) == null) {
				cacheResult(customCategoryProperty);
			}
			else {
				customCategoryProperty.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all custom category properties.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CustomCategoryPropertyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the custom category property.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CustomCategoryProperty customCategoryProperty) {
		entityCache.removeResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			customCategoryProperty.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CustomCategoryPropertyModelImpl)customCategoryProperty,
			true);
	}

	@Override
	public void clearCache(
		List<CustomCategoryProperty> customCategoryProperties) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CustomCategoryProperty customCategoryProperty : customCategoryProperties) {
			entityCache.removeResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
				CustomCategoryPropertyImpl.class,
				customCategoryProperty.getPrimaryKey());

			clearUniqueFindersCache((CustomCategoryPropertyModelImpl)customCategoryProperty,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CustomCategoryPropertyModelImpl customCategoryPropertyModelImpl) {
		Object[] args = new Object[] {
				customCategoryPropertyModelImpl.getUuid(),
				customCategoryPropertyModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			customCategoryPropertyModelImpl, false);

		args = new Object[] {
				customCategoryPropertyModelImpl.getCategoryId(),
				customCategoryPropertyModelImpl.getKey()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CATEGORYIDANDKEY, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY, args,
			customCategoryPropertyModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CustomCategoryPropertyModelImpl customCategoryPropertyModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					customCategoryPropertyModelImpl.getUuid(),
					customCategoryPropertyModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((customCategoryPropertyModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					customCategoryPropertyModelImpl.getOriginalUuid(),
					customCategoryPropertyModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					customCategoryPropertyModelImpl.getCategoryId(),
					customCategoryPropertyModelImpl.getKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CATEGORYIDANDKEY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY, args);
		}

		if ((customCategoryPropertyModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					customCategoryPropertyModelImpl.getOriginalCategoryId(),
					customCategoryPropertyModelImpl.getOriginalKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CATEGORYIDANDKEY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CATEGORYIDANDKEY, args);
		}
	}

	/**
	 * Creates a new custom category property with the primary key. Does not add the custom category property to the database.
	 *
	 * @param CustomCategoryPropertyId the primary key for the new custom category property
	 * @return the new custom category property
	 */
	@Override
	public CustomCategoryProperty create(long CustomCategoryPropertyId) {
		CustomCategoryProperty customCategoryProperty = new CustomCategoryPropertyImpl();

		customCategoryProperty.setNew(true);
		customCategoryProperty.setPrimaryKey(CustomCategoryPropertyId);

		String uuid = PortalUUIDUtil.generate();

		customCategoryProperty.setUuid(uuid);

		customCategoryProperty.setCompanyId(companyProvider.getCompanyId());

		return customCategoryProperty;
	}

	/**
	 * Removes the custom category property with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CustomCategoryPropertyId the primary key of the custom category property
	 * @return the custom category property that was removed
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty remove(long CustomCategoryPropertyId)
		throws NoSuchCustomCategoryPropertyException {
		return remove((Serializable)CustomCategoryPropertyId);
	}

	/**
	 * Removes the custom category property with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the custom category property
	 * @return the custom category property that was removed
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty remove(Serializable primaryKey)
		throws NoSuchCustomCategoryPropertyException {
		Session session = null;

		try {
			session = openSession();

			CustomCategoryProperty customCategoryProperty = (CustomCategoryProperty)session.get(CustomCategoryPropertyImpl.class,
					primaryKey);

			if (customCategoryProperty == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCustomCategoryPropertyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(customCategoryProperty);
		}
		catch (NoSuchCustomCategoryPropertyException nsee) {
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
	protected CustomCategoryProperty removeImpl(
		CustomCategoryProperty customCategoryProperty) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(customCategoryProperty)) {
				customCategoryProperty = (CustomCategoryProperty)session.get(CustomCategoryPropertyImpl.class,
						customCategoryProperty.getPrimaryKeyObj());
			}

			if (customCategoryProperty != null) {
				session.delete(customCategoryProperty);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (customCategoryProperty != null) {
			clearCache(customCategoryProperty);
		}

		return customCategoryProperty;
	}

	@Override
	public CustomCategoryProperty updateImpl(
		CustomCategoryProperty customCategoryProperty) {
		boolean isNew = customCategoryProperty.isNew();

		if (!(customCategoryProperty instanceof CustomCategoryPropertyModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(customCategoryProperty.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(customCategoryProperty);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in customCategoryProperty proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CustomCategoryProperty implementation " +
				customCategoryProperty.getClass());
		}

		CustomCategoryPropertyModelImpl customCategoryPropertyModelImpl = (CustomCategoryPropertyModelImpl)customCategoryProperty;

		if (Validator.isNull(customCategoryProperty.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			customCategoryProperty.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (customCategoryProperty.getCreateDate() == null)) {
			if (serviceContext == null) {
				customCategoryProperty.setCreateDate(now);
			}
			else {
				customCategoryProperty.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!customCategoryPropertyModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				customCategoryProperty.setModifiedDate(now);
			}
			else {
				customCategoryProperty.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (customCategoryProperty.isNew()) {
				session.save(customCategoryProperty);

				customCategoryProperty.setNew(false);
			}
			else {
				customCategoryProperty = (CustomCategoryProperty)session.merge(customCategoryProperty);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CustomCategoryPropertyModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					customCategoryPropertyModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					customCategoryPropertyModelImpl.getUuid(),
					customCategoryPropertyModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { customCategoryPropertyModelImpl.getCategoryId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CATEGORYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYID,
				args);

			args = new Object[] {
					customCategoryPropertyModelImpl.getVocabularyId(),
					customCategoryPropertyModelImpl.getKey(),
					customCategoryPropertyModelImpl.getText()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYIDANDKEYANDTEXT,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((customCategoryPropertyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						customCategoryPropertyModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { customCategoryPropertyModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((customCategoryPropertyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						customCategoryPropertyModelImpl.getOriginalUuid(),
						customCategoryPropertyModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						customCategoryPropertyModelImpl.getUuid(),
						customCategoryPropertyModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((customCategoryPropertyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						customCategoryPropertyModelImpl.getOriginalCategoryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CATEGORYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYID,
					args);

				args = new Object[] {
						customCategoryPropertyModelImpl.getCategoryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CATEGORYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYID,
					args);
			}

			if ((customCategoryPropertyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						customCategoryPropertyModelImpl.getOriginalVocabularyId(),
						customCategoryPropertyModelImpl.getOriginalKey(),
						customCategoryPropertyModelImpl.getOriginalText()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYIDANDKEYANDTEXT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT,
					args);

				args = new Object[] {
						customCategoryPropertyModelImpl.getVocabularyId(),
						customCategoryPropertyModelImpl.getKey(),
						customCategoryPropertyModelImpl.getText()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_VOCABULARYIDANDKEYANDTEXT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_VOCABULARYIDANDKEYANDTEXT,
					args);
			}
		}

		entityCache.putResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
			CustomCategoryPropertyImpl.class,
			customCategoryProperty.getPrimaryKey(), customCategoryProperty,
			false);

		clearUniqueFindersCache(customCategoryPropertyModelImpl, false);
		cacheUniqueFindersCache(customCategoryPropertyModelImpl);

		customCategoryProperty.resetOriginalValues();

		return customCategoryProperty;
	}

	/**
	 * Returns the custom category property with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom category property
	 * @return the custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCustomCategoryPropertyException {
		CustomCategoryProperty customCategoryProperty = fetchByPrimaryKey(primaryKey);

		if (customCategoryProperty == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCustomCategoryPropertyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return customCategoryProperty;
	}

	/**
	 * Returns the custom category property with the primary key or throws a {@link NoSuchCustomCategoryPropertyException} if it could not be found.
	 *
	 * @param CustomCategoryPropertyId the primary key of the custom category property
	 * @return the custom category property
	 * @throws NoSuchCustomCategoryPropertyException if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty findByPrimaryKey(
		long CustomCategoryPropertyId)
		throws NoSuchCustomCategoryPropertyException {
		return findByPrimaryKey((Serializable)CustomCategoryPropertyId);
	}

	/**
	 * Returns the custom category property with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom category property
	 * @return the custom category property, or <code>null</code> if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
				CustomCategoryPropertyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CustomCategoryProperty customCategoryProperty = (CustomCategoryProperty)serializable;

		if (customCategoryProperty == null) {
			Session session = null;

			try {
				session = openSession();

				customCategoryProperty = (CustomCategoryProperty)session.get(CustomCategoryPropertyImpl.class,
						primaryKey);

				if (customCategoryProperty != null) {
					cacheResult(customCategoryProperty);
				}
				else {
					entityCache.putResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
						CustomCategoryPropertyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
					CustomCategoryPropertyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return customCategoryProperty;
	}

	/**
	 * Returns the custom category property with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CustomCategoryPropertyId the primary key of the custom category property
	 * @return the custom category property, or <code>null</code> if a custom category property with the primary key could not be found
	 */
	@Override
	public CustomCategoryProperty fetchByPrimaryKey(
		long CustomCategoryPropertyId) {
		return fetchByPrimaryKey((Serializable)CustomCategoryPropertyId);
	}

	@Override
	public Map<Serializable, CustomCategoryProperty> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CustomCategoryProperty> map = new HashMap<Serializable, CustomCategoryProperty>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CustomCategoryProperty customCategoryProperty = fetchByPrimaryKey(primaryKey);

			if (customCategoryProperty != null) {
				map.put(primaryKey, customCategoryProperty);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
					CustomCategoryPropertyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CustomCategoryProperty)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE_PKS_IN);

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

			for (CustomCategoryProperty customCategoryProperty : (List<CustomCategoryProperty>)q.list()) {
				map.put(customCategoryProperty.getPrimaryKeyObj(),
					customCategoryProperty);

				cacheResult(customCategoryProperty);

				uncachedPrimaryKeys.remove(customCategoryProperty.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CustomCategoryPropertyModelImpl.ENTITY_CACHE_ENABLED,
					CustomCategoryPropertyImpl.class, primaryKey, nullModel);
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
	 * Returns all the custom category properties.
	 *
	 * @return the custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom category properties.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @return the range of custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the custom category properties.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findAll(int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom category properties.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomCategoryPropertyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom category properties
	 * @param end the upper bound of the range of custom category properties (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of custom category properties
	 */
	@Override
	public List<CustomCategoryProperty> findAll(int start, int end,
		OrderByComparator<CustomCategoryProperty> orderByComparator,
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

		List<CustomCategoryProperty> list = null;

		if (retrieveFromCache) {
			list = (List<CustomCategoryProperty>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CUSTOMCATEGORYPROPERTY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CUSTOMCATEGORYPROPERTY;

				if (pagination) {
					sql = sql.concat(CustomCategoryPropertyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomCategoryProperty>)QueryUtil.list(q,
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
	 * Removes all the custom category properties from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CustomCategoryProperty customCategoryProperty : findAll()) {
			remove(customCategoryProperty);
		}
	}

	/**
	 * Returns the number of custom category properties.
	 *
	 * @return the number of custom category properties
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CUSTOMCATEGORYPROPERTY);

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
		return CustomCategoryPropertyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the custom category property persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CustomCategoryPropertyImpl.class.getName());
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
	private static final String _SQL_SELECT_CUSTOMCATEGORYPROPERTY = "SELECT customCategoryProperty FROM CustomCategoryProperty customCategoryProperty";
	private static final String _SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE_PKS_IN = "SELECT customCategoryProperty FROM CustomCategoryProperty customCategoryProperty WHERE CustomCategoryPropertyId IN (";
	private static final String _SQL_SELECT_CUSTOMCATEGORYPROPERTY_WHERE = "SELECT customCategoryProperty FROM CustomCategoryProperty customCategoryProperty WHERE ";
	private static final String _SQL_COUNT_CUSTOMCATEGORYPROPERTY = "SELECT COUNT(customCategoryProperty) FROM CustomCategoryProperty customCategoryProperty";
	private static final String _SQL_COUNT_CUSTOMCATEGORYPROPERTY_WHERE = "SELECT COUNT(customCategoryProperty) FROM CustomCategoryProperty customCategoryProperty WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "customCategoryProperty.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomCategoryProperty exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CustomCategoryProperty exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CustomCategoryPropertyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "key", "text"
			});
}
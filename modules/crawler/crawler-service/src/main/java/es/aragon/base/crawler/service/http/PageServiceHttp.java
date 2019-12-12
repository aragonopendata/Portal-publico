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

package es.aragon.base.crawler.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import es.aragon.base.crawler.service.PageServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link PageServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PageServiceSoap
 * @see HttpPrincipal
 * @see PageServiceUtil
 * @generated
 */
@ProviderType
public class PageServiceHttp {
	public static java.util.List<es.aragon.base.crawler.model.Page> getPagesByKeywords(
		HttpPrincipal httpPrincipal, long companyId, String keywords,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.crawler.model.Page> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(PageServiceUtil.class,
					"getPagesByKeywords", _getPagesByKeywordsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, keywords, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<es.aragon.base.crawler.model.Page>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getPagesCountByKeywords(HttpPrincipal httpPrincipal,
		long companyId, String keywords) {
		try {
			MethodKey methodKey = new MethodKey(PageServiceUtil.class,
					"getPagesCountByKeywords",
					_getPagesCountByKeywordsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<es.aragon.base.crawler.model.Page> getPagesByKeywordsByRootPage(
		HttpPrincipal httpPrincipal, long companyId, String keywords,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<es.aragon.base.crawler.model.Page> orderByComparator,
		long rootPageId) {
		try {
			MethodKey methodKey = new MethodKey(PageServiceUtil.class,
					"getPagesByKeywordsByRootPage",
					_getPagesByKeywordsByRootPageParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, keywords, start, end, orderByComparator,
					rootPageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<es.aragon.base.crawler.model.Page>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getPagesCountByKeywordsByRootPage(
		HttpPrincipal httpPrincipal, long companyId, String keywords,
		long rootPageId) {
		try {
			MethodKey methodKey = new MethodKey(PageServiceUtil.class,
					"getPagesCountByKeywordsByRootPage",
					_getPagesCountByKeywordsByRootPageParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, keywords, rootPageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getParentPageStatus(HttpPrincipal httpPrincipal,
		es.aragon.base.crawler.model.Page page) {
		try {
			MethodKey methodKey = new MethodKey(PageServiceUtil.class,
					"getParentPageStatus", _getParentPageStatusParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, page);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static es.aragon.base.crawler.model.Page deletePage(
		HttpPrincipal httpPrincipal, long pageId, long groupId)
		throws com.liferay.portal.kernel.security.auth.AuthException {
		try {
			MethodKey methodKey = new MethodKey(PageServiceUtil.class,
					"deletePage", _deletePageParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, pageId,
					groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.security.auth.AuthException) {
					throw (com.liferay.portal.kernel.security.auth.AuthException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (es.aragon.base.crawler.model.Page)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PageServiceHttp.class);
	private static final Class<?>[] _getPagesByKeywordsParameterTypes0 = new Class[] {
			long.class, String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getPagesCountByKeywordsParameterTypes1 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getPagesByKeywordsByRootPageParameterTypes2 =
		new Class[] {
			long.class, String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class, long.class
		};
	private static final Class<?>[] _getPagesCountByKeywordsByRootPageParameterTypes3 =
		new Class[] { long.class, String.class, long.class };
	private static final Class<?>[] _getParentPageStatusParameterTypes4 = new Class[] {
			es.aragon.base.crawler.model.Page.class
		};
	private static final Class<?>[] _deletePageParameterTypes5 = new Class[] {
			long.class, long.class
		};
}
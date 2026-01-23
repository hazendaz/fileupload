/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.filters;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import com.googlecode.htmlcompressor.compressor.XmlCompressor;
import com.googlecode.htmlcompressor.compressor.YuiCssCompressor;
import com.googlecode.htmlcompressor.compressor.YuiJavaScriptCompressor;
import com.hazendaz.model.IUser;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;

@WebFilter(urlPatterns = { "/*" })
public class MinimizeFilter implements Filter {

    @Inject
    private Logger logger;

    @Inject
    private IUser user;

    /**
     * The filter configuration object we are associated with. If this value is null, this filter instance is not
     * currently configured.
     */
    private FilterConfig config = null;

    /**
     * Take this filter out of service.
     */
    @Override
    public void destroy() {
        this.config = null;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        this.logger.info("User Name {}", this.user.getName());
        this.logger.info("Request {}", request.toString());
        this.logger.info("Response {}", response.toString());

        if (response instanceof HttpServletResponse) {
            if (request instanceof HttpServletRequest) {

                if ((((HttpServletRequest) request).getRequestURI().contains(".faces")
                        || ((HttpServletRequest) request).getRequestURI().endsWith("/fileupload/")
                        || ((HttpServletRequest) request).getRequestURI().contains("/images/"))
                        && !((HttpServletRequest) request).getRequestURI().contains("js.faces")
                        && !((HttpServletRequest) request).getRequestURI().contains("css.faces")) {
                    chain.doFilter(request, response);
                    return;
                }

                final MinimizeServletResponseWrapper wrappedResponse = new MinimizeServletResponseWrapper(
                        (HttpServletResponse) response);

                this.logger.debug("doFilter gets called with compression");
                try {
                    chain.doFilter(request, wrappedResponse);
                } finally {
                    if (((HttpServletRequest) request).getRequestURI().contains(".js")) {
                        final YuiJavaScriptCompressor compressor = new YuiJavaScriptCompressor();
                        compressor.setDisableOptimizations(false);
                        compressor.setErrorReporter(null);
                        compressor.setLineBreak(-1);
                        compressor.setNoMunge(true);
                        compressor.setPreserveAllSemiColons(true);
                        try {
                            final CharArrayWriter caw = new CharArrayWriter();
                            caw.write(compressor
                                    .compress(new String(wrappedResponse.getDataStream(), StandardCharsets.UTF_8)));
                            response.setContentLength(caw.toString().getBytes(StandardCharsets.UTF_8).length);
                            response.getWriter().print(caw.toString());
                            response.getWriter().close();
                        } catch (final Exception e) {
                            this.logger.error(e.getMessage());
                        }
                    } else if (((HttpServletRequest) request).getRequestURI().contains(".css")
                            || ((HttpServletRequest) request).getRequestURI().contains(".ecss")) {
                        final YuiCssCompressor compressor = new YuiCssCompressor();
                        compressor.setLineBreak(-1);
                        try {
                            final CharArrayWriter caw = new CharArrayWriter();
                            caw.write(compressor
                                    .compress(new String(wrappedResponse.getDataStream(), StandardCharsets.UTF_8)));
                            response.setContentLength(caw.toString().getBytes(StandardCharsets.UTF_8).length);
                            response.getWriter().print(caw.toString());
                            response.getWriter().close();
                        } catch (final Exception e) {
                            this.logger.error(e.getMessage());
                        }
                    } else if (((HttpServletRequest) request).getRequestURI().contains(".html")
                            || ((HttpServletRequest) request).getRequestURI().contains(".xhtml")) {
                        final HtmlCompressor compressor = new HtmlCompressor();
                        compressor.setCompressJavaScript(false);
                        compressor.setCompressCss(false);
                        compressor.setRemoveComments(false);
                        compressor.setSimpleDoctype(false);
                        compressor.setRemoveQuotes(false);
                        try {
                            final CharArrayWriter caw = new CharArrayWriter();
                            caw.write(compressor
                                    .compress(new String(wrappedResponse.getDataStream(), StandardCharsets.UTF_8)));
                            response.setContentLength(caw.toString().getBytes(StandardCharsets.UTF_8).length);
                            response.getWriter().print(caw.toString());
                            response.getWriter().close();
                        } catch (final Exception e) {
                            this.logger.error(e.getMessage());
                        }
                    } else if (((HttpServletRequest) request).getRequestURI().contains(".xml")) {
                        final XmlCompressor compressor = new XmlCompressor();
                        compressor.setEnabled(true);
                        compressor.setRemoveComments(true);
                        compressor.setRemoveIntertagSpaces(false);
                        try {
                            final CharArrayWriter caw = new CharArrayWriter();
                            caw.write(compressor
                                    .compress(new String(wrappedResponse.getDataStream(), StandardCharsets.UTF_8)));
                            response.setContentLength(caw.toString().getBytes(StandardCharsets.UTF_8).length);
                            response.getWriter().print(caw.toString());
                            response.getWriter().close();
                        } catch (final Exception e) {
                            this.logger.error(e.getMessage());
                        }
                    } else {
                        try {
                            final CharArrayWriter caw = new CharArrayWriter();
                            caw.write(new String(wrappedResponse.getDataStream(), StandardCharsets.UTF_8));
                            response.setContentLength(caw.toString().getBytes(StandardCharsets.UTF_8).length);
                            response.getWriter().print(caw.toString());
                            response.getWriter().close();
                        } catch (final Exception e) {
                            this.logger.error(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    /**
     * Return filter config Required by Weblogic 6.1
     *
     * @return FilterConfig
     */
    public FilterConfig getFilterConfig() {
        return this.config;
    }

    /**
     * Place this filter into service.
     *
     * @param filterConfig
     *            The filter configuration object
     */
    @Override
    public void init(final FilterConfig filterConfig) {
        this.config = filterConfig;
    }

    /**
     * Set filter config This function is equivalent to init. Required by Weblogic 6.1
     *
     * @param filterConfig
     *            The filter configuration object
     */
    public void setFilterConfig(final FilterConfig filterConfig) {
        this.init(filterConfig);
    }

}

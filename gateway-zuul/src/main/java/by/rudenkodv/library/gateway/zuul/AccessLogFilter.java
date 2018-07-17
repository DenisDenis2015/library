package by.rudenkodv.library.gateway.zuul;


//@Component
public class AccessLogFilter /*extends ZuulFilter*/ {

   /* Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

        logger.info(
                "REQUEST :: < " + request.getScheme() + " " + request.getLocalAddr() + ":" + request.getLocalPort());
        logger.info(
                "REQUEST :: < " + request.getMethod() + " " + request.getRequestURI() + " " + request.getProtocol());
        logger.info("RESPONSE:: > HTTP:" + response.getStatus());

        return null;
    }*/
}
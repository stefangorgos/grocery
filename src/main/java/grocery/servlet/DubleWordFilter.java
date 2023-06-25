package grocery.servlet;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;

@WebFilter
public class DubleWordFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 8191936293413881669L;

    public DubleWordFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
		int i = 0;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		int i=0;
		CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, wrapper/* response */);
		
		PrintWriter responseWriter = response.getWriter();
        
        if (wrapper.getContentType().contains("text/html")) {
            CharArrayWriter charWriter = new CharArrayWriter();
            String originalContent = wrapper.toString();
             
            int indexOfCloseBodyTag = originalContent.indexOf("</body>") - 1;
              
            charWriter.write(originalContent.substring(0, indexOfCloseBodyTag));
              
            String copyrightInfo = "<p>Copyright CodeJava.net</p>";
            String closeHTMLTags = "</body></html>";
             
            charWriter.write(copyrightInfo);
            charWriter.write(closeHTMLTags);
              
            String alteredContent = charWriter.toString();
            response.setContentLength(alteredContent.length());
            responseWriter.write(alteredContent);          
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		int j = 0;
	}
	
	public class CharResponseWrapper extends HttpServletResponseWrapper {
	    private CharArrayWriter writer;
	      
	    public CharResponseWrapper(HttpServletResponse response) {
	        super(response);
	        writer = new CharArrayWriter();
	    }
	      
	    public PrintWriter getWriter() {
	        return new PrintWriter(writer);
	    }
	      
	    public String toString() {
	        return writer.toString();
	    }
	  
	}

}

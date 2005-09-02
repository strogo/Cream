package org.campware.cream.modules.actions;

/* ====================================================================
 * Copyright (C) 2003-2005  Media Development Loan Fund
 *
 *  * contact: contact@campware.org - http://www.campware.org
 * Campware encourages further development. Please let us know.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

import java.util.Date;
import java.util.Enumeration;

import org.apache.velocity.context.Context;

import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;

import java.sql.Connection;

import org.campware.cream.om.NewsSubscription;
import org.campware.cream.om.NewsSubscriptionPeer;
import org.campware.cream.om.Project;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.Product;
import org.campware.cream.om.ProductPeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class NewsletterSubscription extends CreamPublicAction
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("NEWSLETTER_SUBSCRIPTION");
    }
    /**
     * This simply takes an entry from the web form and
     * inserts it directly into the database.
     *
     */
    public void doSubscribe(RunData data, Context context)
        throws Exception
    {

		String urlOk= data.getParameters().getString("urlok", "NONE");
		String urlError= data.getParameters().getString("urlerror", "NONE");

		try{

    		String subsEmail= data.getParameters().getString("email");
    		String subsNotes= data.getParameters().getString("notes");
    		int subsStatus= data.getParameters().getInt("status", 30);
    		String projCode= data.getParameters().getString("campaignid", "NONE");
    		int projId = 1000;

            if (!projCode.equalsIgnoreCase("NONE")){
            	Criteria projCriteria = new Criteria();
            	projCriteria.add(ProjectPeer.PROJECT_CODE, projCode);
            	Project projEntry = (Project) ProjectPeer.doSelect(projCriteria).get(0);
            	projId= projEntry.getProjectId();
            }
    		
            ParameterParser pp= data.getParameters();
            Enumeration paramKeys= pp.keys();
            
    	    while(paramKeys.hasMoreElements()) {
    	        String paramName = paramKeys.nextElement().toString();
    	        if(paramName.startsWith("productid")) {	
    	            String suffix=paramName.substring(9, paramName.length());
    	            
    	    		NewsSubscription entry = new NewsSubscription();
    	    		int prodId=1000;
    	    		String prodCode= pp.getString("productid" + suffix, "NONE");
    	            if (!prodCode.equalsIgnoreCase("NONE")){
    	            	Criteria prodCriteria = new Criteria();
    	            	prodCriteria.add(ProductPeer.PRODUCT_CODE, prodCode);
    	            	Product prodEntry = (Product) ProductPeer.doSelect(prodCriteria).get(0);
    	            	prodId= prodEntry.getProductId();
    	            }
    	            entry.setNewsSubsCode(getTempCode());

    	            entry.setIssuedDate(new Date());
    	            entry.setCreatedBy("system");
    	            entry.setCreated(new Date());
    	            entry.setModifiedBy("system");
    	            entry.setModified(new Date());

    	            entry.setEmail(subsEmail);
    	            entry.setStatus(subsStatus);
    	            entry.setNotes(subsNotes);
    	            entry.setProductId(prodId);
    	            entry.setProjectId(projId);

    	            Connection conn = Transaction.begin(NewsSubscriptionPeer.DATABASE_NAME);
    	            boolean success = false;
    	            try {
    	                entry.save(conn);
    	                entry.setNewsSubsCode(getRowCode("NS", entry.getNewsSubsId()));
    	                entry.save(conn);
    	                Transaction.commit(conn);
    	                success = true;

    	            } finally {
    	                if (!success) Transaction.safeRollback(conn);
    	            }

                }
            }

            if (!urlOk.equalsIgnoreCase("NONE")){
	    	    data.setRedirectURI (urlOk);
	    	    data.setStatusCode(302);
            }else{
              this.setTemplate( data, "NewsletterSubscribeOk.vm");
            }

    	}catch (Exception e){
            if (!urlError.equalsIgnoreCase("NONE")){
	    	    data.setRedirectURI (urlError);
	    	    data.setStatusCode(302);
            }else{
              this.setTemplate( data, "NewsletterSubscribeError.vm");
            }
    	}
    	
    	
    }

    /**
     * Delete a record from the database using
     * the unique id gleaned from the web form.
     */
    public void doUnsubscribe(RunData data, Context context)
        throws Exception
    {

		String urlOk= Turbine.getConfiguration().getString("news.unsubscribe.url.ok", "Default");
		String urlError= Turbine.getConfiguration().getString("news.unsubscribe.url.error", "Default");

		try{
			Criteria criteria = new Criteria();
	        criteria.add(NewsSubscriptionPeer.NEWS_SUBS_CODE, data.getParameters().getString("subscriptionid"));
	        criteria.add(NewsSubscriptionPeer.EMAIL, data.getParameters().getString("email"));
	    	NewsSubscription subsEntry = (NewsSubscription) NewsSubscriptionPeer.doSelect(criteria).get(0);

	    	subsEntry.setStatus(50);
	    	subsEntry.setClosedDate(new Date());
	    	subsEntry.setModifiedBy("system");
	    	subsEntry.setModified(new Date());

	    	subsEntry.setModified(true);
	    	subsEntry.setNew(false);
	    	subsEntry.save();
	    	
            if (!urlOk.equalsIgnoreCase("Default")){
	    	    data.setRedirectURI (urlOk);
	    	    data.setStatusCode(302);
            }else{
              this.setTemplate( data, "NewsletterUnsubscribeOk.vm");
            }
		}catch (Exception e){
	        if (!urlError.equalsIgnoreCase("Default")){
	    	    data.setRedirectURI (urlError);
	    	    data.setStatusCode(302);
	        }else{
	          this.setTemplate( data, "NewsletterUnsubscribeError.vm");
	        }
		}
    	
    }

}

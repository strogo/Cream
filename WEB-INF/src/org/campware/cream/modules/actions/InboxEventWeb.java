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

import org.campware.cream.om.Customer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.InboxEvent;
import org.campware.cream.om.InboxEventPeer;
import org.campware.cream.om.Project;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.Product;
import org.campware.cream.om.ProductPeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class InboxEventWeb extends CreamPublicAction
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("INBOX_EVENT_WEB");
    }
    /**
     * This simply takes an entry from the web form and
     * inserts it directly into the database.
     *
     */
    public void doRequest(RunData data, Context context)
        throws Exception
    {

		String urlOk= data.getParameters().getString("urlok", "NONE");
		String urlError= data.getParameters().getString("urlerror", "NONE");

		try{

    		int reqStatus= data.getParameters().getInt("status", 30);
    		int reqEventType= data.getParameters().getInt("eventtype", 10);
    		int reqChannel= 60;
    		String reqCustom1= data.getParameters().getString("custom1", "");
    		String reqCustom2= data.getParameters().getString("custom2", "");
    		String reqCustom3= data.getParameters().getString("custom3", "");
    		String reqCustom4= data.getParameters().getString("custom4", "");
    		String reqCustom5= data.getParameters().getString("custom5", "");
    		String reqCustom6= data.getParameters().getString("custom6", "");
    		String reqEmail= data.getParameters().getString("email");
    		String reqSubject= data.getParameters().getString("subject", " ---");
    		String reqBody= data.getParameters().getString("body");
    		String prodCode= data.getParameters().getString("productid", "NONE");
    		String projCode= data.getParameters().getString("campaignid", "NONE");
    		int custId = 1000;
    		int prodId = 1000;
    		int projId = 1000;

            if (!prodCode.equalsIgnoreCase("NONE")){
            	Criteria prodCriteria = new Criteria();
            	prodCriteria.add(ProductPeer.PRODUCT_CODE, prodCode);
            	Product prodEntry = (Product) ProductPeer.doSelect(prodCriteria).get(0);
            	prodId= prodEntry.getProductId();
            }
    		
            if (!projCode.equalsIgnoreCase("NONE")){
            	Criteria projCriteria = new Criteria();
            	projCriteria.add(ProjectPeer.PROJECT_CODE, projCode);
            	Project projEntry = (Project) ProjectPeer.doSelect(projCriteria).get(0);
            	projId= projEntry.getProjectId();
            }

            // find if customer exists
            Criteria custCriteria = new Criteria();
            custCriteria.add(CustomerPeer.EMAIL, (Object)reqEmail, Criteria.EQUAL);
			if (CustomerPeer.doSelect(custCriteria).size()>0){
               log.debug("From known customer");
               Customer myDistrib = (Customer) CustomerPeer.doSelect(custCriteria).get(0);
               custId=myDistrib.getCustomerId();
			}
            
            
    	    		InboxEvent entry = new InboxEvent();
    	            entry.setInboxEventCode(getTempCode());

    	            entry.setIssuedDate(new Date());
    	            entry.setCreatedBy("system");
    	            entry.setCreated(new Date());
    	            entry.setModifiedBy("system");
    	            entry.setModified(new Date());

    	            entry.setStatus(reqStatus);
    	            entry.setEventType(reqEventType);
    	            entry.setEventChannel(reqChannel);
    	            entry.setCustom1(reqCustom1);
    	            entry.setCustom2(reqCustom2);
    	            entry.setCustom3(reqCustom3);
    	            entry.setCustom4(reqCustom4);
    	            entry.setCustom5(reqCustom5);
    	            entry.setCustom6(reqCustom6);

    				entry.setEmailFormat(10);
    				entry.setSenderName("");
    				entry.setSenderReplyTo(reqEmail);
    				entry.setSentTime(new Date());
    	            
    	            entry.setSenderEmail(reqEmail);
    	            entry.setSubject(reqSubject);
    	            entry.setBody(reqBody);
    	            entry.setCustomerId(custId);
    	            entry.setProductId(prodId);
    	            entry.setProjectId(projId);

    	            Connection conn = Transaction.begin(InboxEventPeer.DATABASE_NAME);
    	            boolean success = false;
    	            try {
    	                entry.save(conn);
    	                entry.setInboxEventCode(getRowCode("IE", entry.getInboxEventId()));
    	                entry.save(conn);
    	                Transaction.commit(conn);
    	                success = true;

    	            } finally {
    	                if (!success) Transaction.safeRollback(conn);
    	            }


            if (!urlOk.equalsIgnoreCase("NONE")){
	    	    data.setRedirectURI (urlOk);
	    	    data.setStatusCode(302);
            }else{
              this.setTemplate( data, "InboxEventWebOk.vm");
            }

    	}catch (Exception e){
            if (!urlError.equalsIgnoreCase("NONE")){
	    	    data.setRedirectURI (urlError);
	    	    data.setStatusCode(302);
            }else{
              this.setTemplate( data, "InboxEventWebError.vm");
            }
    	}
    	
    	
    }


}

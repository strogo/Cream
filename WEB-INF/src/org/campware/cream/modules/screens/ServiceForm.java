package org.campware.cream.modules.screens;

/* ====================================================================
 * Copyright (C) 2003  Media Development Loan Fund
 *
 * Contact: contact@campware.org - http://www.campware.org
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

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

import org.campware.cream.om.Service;
import org.campware.cream.om.ServicePeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.Shipment;
import org.campware.cream.om.Sorder;
import org.campware.cream.om.SorderPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class ServiceForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("SERVICE");
        setIdName(ServicePeer.SERVICE_ID);
        setFormIdName("serviceid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            Service entry = (Service) ServicePeer.doSelect(criteria).get(0);
            context.put("entry", entry);
            context.put("entryitems", entry.getServiceItems());

            Criteria projcrit = new Criteria();
            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.STATUS, new Integer(30), Criteria.EQUAL);
            Criteria.Criterion pj3 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(entry.getProjectId()), Criteria.EQUAL);
            projcrit.add( pj1.or(pj2.or(pj3)));
            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
            context.put("projects", ProjectPeer.doSelect(projcrit));

            Criteria custcrit = new Criteria();
            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.STATUS, new Integer(30), Criteria.EQUAL);
            Criteria.Criterion cu3 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(entry.getCustomerId()), Criteria.EQUAL);
            Criteria.Criterion cu4 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(entry.getRecipientId()), Criteria.EQUAL);
            custcrit.add( cu1.or(cu2.or(cu3.or(cu4))));
            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
            context.put("customers", CustomerPeer.doSelect(custcrit));

            Criteria ordcrit = new Criteria();
            Criteria.Criterion a1 = ordcrit.getNewCriterion(SorderPeer.SORDER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion a2 = ordcrit.getNewCriterion(SorderPeer.SORDER_ID, new Integer(entry.getSorderId()), Criteria.EQUAL);
            Criteria.Criterion a3 = ordcrit.getNewCriterion(SorderPeer.CUSTOMER_ID, new Integer(entry.getCustomerId()), Criteria.EQUAL);
            Criteria.Criterion a4 = ordcrit.getNewCriterion(SorderPeer.STATUS, new Integer(30), Criteria.EQUAL);
            ordcrit.add( a1.or( a2.or(a3.and(a4))));
            ordcrit.addAscendingOrderByColumn(SorderPeer.SORDER_CODE);
            context.put("orders", SorderPeer.doSelect(ordcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getNew(Context context)
    {
        try
        {
            Service entry = new Service();
            context.put("entry", entry);

            Criteria projcrit = new Criteria();
            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.STATUS, new Integer(30), Criteria.EQUAL);
            projcrit.add( pj1.or(pj2));
            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
            context.put("projects", ProjectPeer.doSelect(projcrit));

            Criteria custcrit = new Criteria();
            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.STATUS, new Integer(30), Criteria.EQUAL);
            custcrit.add( cu1.or(cu2));
            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
            context.put("customers", CustomerPeer.doSelect(custcrit));

            Criteria ordcrit = new Criteria();
            ordcrit.add(SorderPeer.SORDER_ID, 1000, Criteria.EQUAL);
            context.put("orders", SorderPeer.doSelect(ordcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

	protected boolean getNewRelated(int relform, int relid, Context context)
	{
		try
		{
			Service entry = new Service();

			if (relform==CUSTOMER){
				
				entry.setCustomerId(relid);
				entry.setRecipientId(relid);

	            Criteria projcrit = new Criteria();
	            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            projcrit.add( pj1.or(pj2));
	            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
	            context.put("projects", ProjectPeer.doSelect(projcrit));

	            Criteria custcrit = new Criteria();
	            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(relid), Criteria.EQUAL);
	            custcrit.add( cu1.or(cu2));
	            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
	            context.put("customers", CustomerPeer.doSelect(custcrit));
		
	            Criteria ordcrit = new Criteria();
	            Criteria.Criterion or1 = ordcrit.getNewCriterion(SorderPeer.SORDER_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion or2 = ordcrit.getNewCriterion(SorderPeer.CUSTOMER_ID, new Integer(relid), Criteria.EQUAL);
	            Criteria.Criterion or3 = ordcrit.getNewCriterion(SorderPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            ordcrit.add( or1.or(or2.and(or3)));
	            ordcrit.addAscendingOrderByColumn(SorderPeer.SORDER_CODE);
	            context.put("orders", SorderPeer.doSelect(ordcrit));
	            
			}else if(relform==PROJECT){
				
				entry.setProjectId(relid);

	            Criteria projcrit = new Criteria();
	            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(relid), Criteria.EQUAL);
	            projcrit.add( pj1.or( pj2));
	            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
	            context.put("projects", ProjectPeer.doSelect(projcrit));

	            Criteria custcrit = new Criteria();
	            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            custcrit.add( cu1.or(cu2));
	            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
	            context.put("customers", CustomerPeer.doSelect(custcrit));
		
	            Criteria ordcrit = new Criteria();
	            ordcrit.add(SorderPeer.SORDER_ID, 1000, Criteria.EQUAL);
	            context.put("orders", SorderPeer.doSelect(ordcrit));

			}else if(relform==SORDER){
				
				Criteria criteria = new Criteria();
				criteria.add(SorderPeer.SORDER_ID, relid);
				Sorder relEntry = (Sorder) SorderPeer.doSelect(criteria).get(0);

				entry.setSorderId(relid);
				entry.setCustomerId(relEntry.getCustomerId());
				entry.setRecipientId(relEntry.getRecipientId());
				entry.setProjectId(relEntry.getProjectId());

	            Criteria projcrit = new Criteria();
	            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(relEntry.getProjectId()), Criteria.EQUAL);
	            projcrit.add( pj1.or( pj2));
	            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
	            context.put("projects", ProjectPeer.doSelect(projcrit));

	            Criteria custcrit = new Criteria();
	            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(relEntry.getCustomerId()), Criteria.EQUAL);
	            Criteria.Criterion cu3 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(relEntry.getRecipientId()), Criteria.EQUAL);
	            custcrit.add( cu1.or(cu2.or(cu3)));
	            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
	            context.put("customers", CustomerPeer.doSelect(custcrit));
		
	            Criteria ordcrit = new Criteria();
	            Criteria.Criterion or1 = ordcrit.getNewCriterion(SorderPeer.SORDER_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion or2 = ordcrit.getNewCriterion(SorderPeer.SORDER_ID, new Integer(relid), Criteria.EQUAL);
	            ordcrit.add( or1.or( or2));
	            custcrit.addAscendingOrderByColumn(SorderPeer.SORDER_CODE);
	            context.put("orders", SorderPeer.doSelect(ordcrit));

	            context.put("entryitems", relEntry.getSorderItems());

			}

			context.put("entry", entry);
            
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
    
    
    protected boolean getLookups(Context context)
    {
        try
        {
            Criteria prodcrit = new Criteria();
            Criteria.Criterion a1 = prodcrit.getNewCriterion(ProductPeer.PRODUCT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion a2 = prodcrit.getNewCriterion(ProductPeer.PRODUCT_TYPE, new Integer(20), Criteria.EQUAL);
            prodcrit.add( a1.or( a2));
            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
            context.put("products", ProductPeer.doSelect(prodcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


}

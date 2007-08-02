package org.campware.cream.modules.screens;

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

import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.Turbine;

import org.apache.velocity.context.Context;

import org.campware.cream.om.Customer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.LanguagePeer;
import org.campware.cream.om.IndustryPeer;
import org.campware.cream.om.LeadSourcePeer;
import org.campware.cream.om.CustomerCategoryPeer;
import org.campware.cream.om.HouseholdCategoryPeer;
import org.campware.cream.om.EducationCategoryPeer;
import org.campware.cream.om.AgeCategoryPeer;
import org.campware.cream.om.EmployeeNoCategoryPeer;
import org.campware.cream.om.RevenueCategoryPeer;
import org.campware.cream.om.CountryPeer;
import org.campware.cream.om.RegionPeer;
import org.campware.cream.om.CustomerDocPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class CustomerForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(ENTITY);
        setModuleName("CUSTOMER");
        setIdName(CustomerPeer.CUSTOMER_ID);
        setFormIdName("customerid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            Customer entry = (Customer) CustomerPeer.doSelect(criteria).get(0);
            context.put("entry", entry);

      		boolean bCmsIntegration = Turbine.getConfiguration().getBoolean("cms.integration.enabled", false);

      		if (bCmsIntegration){
                context.put("cmsintegration", "1");
      		}else{
                context.put("cmsintegration", "0");
      		}

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
            Customer entry = new Customer();
            context.put("entry", entry);

      		boolean bCmsIntegration = Turbine.getConfiguration().getBoolean("cms.integration.enabled", false);

      		if (bCmsIntegration){
                context.put("cmsintegration", "1");
      		}else{
                context.put("cmsintegration", "0");
      		}

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
            Criteria langcrit = new Criteria();
            langcrit.add(LanguagePeer.LANGUAGE_ID, 999, Criteria.GREATER_THAN);
            langcrit.addAscendingOrderByColumn(LanguagePeer.LANGUAGE_NAME);
            context.put("languages", LanguagePeer.doSelect(langcrit));

            Criteria indcrit = new Criteria();
            indcrit.add(IndustryPeer.INDUSTRY_ID, 999, Criteria.GREATER_THAN);
            indcrit.addAscendingOrderByColumn(IndustryPeer.INDUSTRY_NAME);
            context.put("industries", IndustryPeer.doSelect(indcrit));

            Criteria leadcrit = new Criteria();
            leadcrit.add(LeadSourcePeer.LEAD_SOURCE_ID, 999, Criteria.GREATER_THAN);
            leadcrit.addAscendingOrderByColumn(LeadSourcePeer.LEAD_SOURCE_NAME);
            context.put("leadsources", LeadSourcePeer.doSelect(leadcrit));

            Criteria custcatcrit = new Criteria();
            custcatcrit.add(CustomerCategoryPeer.CUSTOMER_CAT_ID, 999, Criteria.GREATER_THAN);
            custcatcrit.addAscendingOrderByColumn(CustomerCategoryPeer.CUSTOMER_CAT_NAME);
            context.put("customercats", CustomerCategoryPeer.doSelect(custcatcrit));

            Criteria countrycrit = new Criteria();
            countrycrit.add(CountryPeer.COUNTRY_ID, 999, Criteria.GREATER_THAN);
            countrycrit.addAscendingOrderByColumn(CountryPeer.COUNTRY_NAME);
            context.put("countries", CountryPeer.doSelect(countrycrit));

            Criteria regioncrit = new Criteria();
            regioncrit.add(RegionPeer.REGION_ID, 999, Criteria.GREATER_THAN);
            regioncrit.addAscendingOrderByColumn(RegionPeer.REGION_NAME);
            context.put("regions", RegionPeer.doSelect(regioncrit));

            Criteria incomecrit = new Criteria();
            incomecrit.add(HouseholdCategoryPeer.HOUSEHOLD_CAT_ID, 999, Criteria.GREATER_THAN);
            incomecrit.addAscendingOrderByColumn(HouseholdCategoryPeer.HOUSEHOLD_CAT_NAME);
            context.put("incomes", HouseholdCategoryPeer.doSelect(incomecrit));

            Criteria educrit = new Criteria();
            educrit.add(EducationCategoryPeer.EDUCATION_CAT_ID, 999, Criteria.GREATER_THAN);
            educrit.addAscendingOrderByColumn(EducationCategoryPeer.EDUCATION_CAT_NAME);
            context.put("educations", EducationCategoryPeer.doSelect(educrit));

            Criteria agecrit = new Criteria();
            agecrit.add(AgeCategoryPeer.AGE_CAT_ID, 999, Criteria.GREATER_THAN);
            agecrit.addAscendingOrderByColumn(AgeCategoryPeer.AGE_CAT_NAME);
            context.put("agecats", AgeCategoryPeer.doSelect(agecrit));

            Criteria emplocrit = new Criteria();
            emplocrit.add(EmployeeNoCategoryPeer.EMPLOYEE_NO_CAT_ID, 999, Criteria.GREATER_THAN);
            emplocrit.addAscendingOrderByColumn(EmployeeNoCategoryPeer.EMPLOYEE_NO_CAT_NAME);
            context.put("employeecats", EmployeeNoCategoryPeer.doSelect(emplocrit));

            Criteria revenuecrit = new Criteria();
            revenuecrit.add(RevenueCategoryPeer.REVENUE_CAT_ID, 999, Criteria.GREATER_THAN);
            revenuecrit.addAscendingOrderByColumn(RevenueCategoryPeer.REVENUE_CAT_NAME);
            context.put("revenues", RevenueCategoryPeer.doSelect(revenuecrit));


            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}

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

import org.apache.velocity.context.Context;
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
import org.campware.cream.modules.upload.UploadHandler;
/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class ImportSQL extends CreamAction
{

    /**
     * Update a record in the database with the
     * information present in the web form.
     *
     * Again, this is merely an example. The data
     * should be checked before being allowed
     * into the database.
     */
    public void doUpdate(RunData data, Context context)
        throws Exception
    {
            String out = UploadHandler.doPost(data);
            data.setMessage(out == null || out.length() == 0 ? "No Input!" : out);
            this.setTemplate( data, "CreamError.vm");
    }

    protected boolean isAuthorized( RunData data ) throws Exception
    {
        boolean isAuthorized = false;

        AccessControlList acl = data.getACL();
        
        if (data.getUser().hasLoggedIn())
        {
            int imptype = data.getParameters().getInt("type");
        	
			if (imptype==10 && (acl.hasPermission( "CUSTOMER_MODIFY") || acl.hasRole("turbine_root")))
			{
				isAuthorized = true;
			}
			else if (imptype==20 && (acl.hasPermission( "PRODUCT_MODIFY") || acl.hasRole("turbine_root")))
			{
				isAuthorized = true;
			}
			else
			{
				isAuthorized = false;
				data.setMessage("Sorry, you don't have permission for this operation!");
				data.setScreenTemplate("CreamError.vm");

			}
		}
		else
		{
			data.setScreenTemplate(Turbine.getConfiguration().getString("template.login"));

			isAuthorized = false;
        }

        return isAuthorized;
    }

    
}

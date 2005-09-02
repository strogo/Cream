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

import org.apache.turbine.util.RunData;
import org.campware.cream.om.Notification;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class NotificationSQL extends CreamAction
{
    protected void initScreen()
    {
        setModuleType(SYSTEM);
        setModuleName("NOTIFICATION");
    }

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
        Notification entry = new Notification();
        
        String not1= data.getParameters().getString("notification1");
        String not2= data.getParameters().getString("notification2");
        String not3= data.getParameters().getString("notification3");
        
        String subj1= data.getParameters().getString("subject1");
        String subj2= data.getParameters().getString("subject2");
        String subj3= data.getParameters().getString("subject3");
        
        Notification entry1 = new Notification();
        
        entry1.setNotificationId(1001);
        entry1.setNotificationType(10);
        entry1.setSubject(subj1);
        entry1.setBody(not1);
        
        entry1.setModified(true);
        entry1.setNew(false);
        entry1.save();

        Notification entry2 = new Notification();
        
        entry2.setNotificationId(1002);
        entry2.setNotificationType(20);
        entry2.setSubject(subj2);
        entry2.setBody(not2);
        
        entry2.setModified(true);
        entry2.setNew(false);
        entry2.save();

        Notification entry3 = new Notification();
        
        entry3.setNotificationId(1003);
        entry3.setNotificationType(30);
        entry3.setSubject(subj3);
        entry3.setBody(not3);
        
        entry3.setModified(true);
        entry3.setNew(false);
        entry3.save();

    }


}

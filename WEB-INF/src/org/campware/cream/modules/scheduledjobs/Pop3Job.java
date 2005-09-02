package org.campware.cream.modules.scheduledjobs;

/* ====================================================================
 * Copyright (C) 2003-2005  Media Development Loan Fund
 *
 *  * contact: contact@campware.org - http://www.campware.org
 * Campware encourages further development. Please let us know.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public Licensede
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

//JDK
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.*;
import java.text.ParseException;
import java.lang.System;
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

//Turbine
import org.apache.turbine.Turbine;
import org.apache.turbine.modules.ScheduledJob;
import org.apache.turbine.services.schedule.JobEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import java.sql.Connection;

import org.campware.cream.om.InboxEvent;
import org.campware.cream.om.InboxEventPeer;
import org.campware.cream.om.InboxAttachment;
import org.campware.cream.om.Customer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.modules.util.Base64;
import java.net.URLEncoder;

/**
 * Pop3 Job.
 *
 * Retrieve new messages from POP3 server
 * @author <a href="mailto:pandzic@volny.cz">Nenad Pandzic</a>
 */
public class Pop3Job extends ScheduledJob
{
	/** Logging */
	private static Log log = LogFactory.getLog(Pop3Job.class);

	private int taskcount = 0;

	/**
	 * Constructor
	 */
	 public Pop3Job()
	 {
		 //do Task initialization here
	 }


	/**
	 * Run the Jobentry from the scheduler queue.
	 * From ScheduledJob.
	 *
	 * @param job The job to run.
	 */
	public void run( JobEntry job ) throws Exception
	{
		// First we resolve online subscriptions
		doReceiveMessages();
	}


	private void doReceiveMessages() throws Exception{


          log.debug("Checking mail ");

		String host = Turbine.getConfiguration().getString("mail.pop3.host");
		String username = Turbine.getConfiguration().getString("mail.pop3.user");
		String password = Turbine.getConfiguration().getString("mail.pop3.password");

		// Create empty properties
		Properties props = new Properties();

		// Get session
		Session session = Session.getDefaultInstance(props, null);

		// Get the store
		Store store = session.getStore("pop3");

		// Connect to store
		store.connect(host, username, password);

		// Get folder
		Folder folder = store.getFolder("INBOX");

		// Open read-only
		folder.open(Folder.READ_WRITE);

		// Get attributes & flags for all messages
		//
		Message[] messages = folder.getMessages();
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.FLAGS);
		fp.add("X-Mailer");
		folder.fetch(messages, fp);

		for (int i = 0; i < messages.length; i++) {
			
			log.debug("Retrieving message "+i);

			// Process each message
			//
            InboxEvent entry = new InboxEvent();
			Address fromAddress= new InternetAddress();
			String from= new String();
			String name= new String();
			String email= new String();
			String replyTo= new String();
			String subject= new String();
			String content= new String();
			Date sentDate= new Date();
			int emailformat= 10;

            Message m = messages[i];

            // and now, handle the content
            Object o = m.getContent();

            // find content type
            if (m.isMimeType("text/plain")) {
            	content = "<PRE style=\"font-size: 12px;\">" + (String)o + "</PRE>";
            	emailformat=10;
            } else if (m.isMimeType("text/html")) {
            	content = (String)o;
            	emailformat=20;
            } else if (m.isMimeType("text/*")) {
            	content = (String)o;
            	emailformat=30;
            } else if (m.isMimeType("multipart/alternative")) {
	            try {
		              content = handleAlternative(o, content);
		              emailformat=20;
		            }
		            catch (Exception ex) {
		              content="Problem with the message format. Messssage has left on the email server.";
		              emailformat=50;
		              log.error(ex.getMessage(),ex);
		            }
            } else if (m.isMimeType("multipart/*")) {
	            try {
	              content = handleMulitipart(o, content, entry);
	              emailformat=40;
	            }
	            catch (Exception ex) {
	              content="Problem with the message format. Messssage has left on the email server.";
	              emailformat=50;
	              log.error(ex.getMessage(),ex);
	            }
            } else {
            	content="Problem with the message format. Messssage has left on the email server.";
            	emailformat=50;
            	log.debug("Could not handle properly");
            }

            email=  ((InternetAddress)m.getFrom()[0]).getAddress();
            name=  ((InternetAddress)m.getFrom()[0]).getPersonal();
            replyTo=  ((InternetAddress)m.getReplyTo()[0]).getAddress();
            sentDate= m.getSentDate();
            subject= m.getSubject();

            log.debug("Got message "+email+" "+name+" "+subject+" "+content);


            // find if customer exists
            Criteria criteria = new Criteria();
			criteria.add(CustomerPeer.EMAIL, (Object)email, Criteria.EQUAL);
			if (CustomerPeer.doSelect(criteria).size()>0){
               log.debug("From known customer");
               Customer myDistrib = (Customer) CustomerPeer.doSelect(criteria).get(0);
               entry.setCustomerId(myDistrib.getCustomerId());
			}

			entry.setInboxEventCode(getTempCode());
			entry.setEventType(10);
			entry.setEventChannel(10);
			entry.setEmailFormat(emailformat);
			entry.setSubject(subject);
			entry.setSenderEmail(email);
			entry.setSenderName(name);
			entry.setSenderReplyTo(replyTo);
			entry.setSentTime(sentDate);
            entry.setBody(content);
			entry.setIssuedDate(new Date());
			entry.setCreatedBy("system");
			entry.setCreated(new Date());
			entry.setModifiedBy("system");
			entry.setModified(new Date());

			Connection conn = Transaction.begin(InboxEventPeer.DATABASE_NAME);
			boolean success = false;
			try {
				entry.save(conn);
				entry.setInboxEventCode(getRowCode("IE", entry.getInboxEventId()));
				entry.save(conn);
				Transaction.commit(conn);
				success = true;
			} finally {
                log.debug("Succcessfully stored in db: "+success);
				if (!success) Transaction.safeRollback(conn);
			}

			if (emailformat!=50){
				m.setFlag(Flags.Flag.DELETED, true);
			}
		}

		// Close pop3 connection
		folder.close(true);
		store.close();

	}

	private String handleAlternative(Object o, String content) throws Exception{

		Multipart multipart = (Multipart) o;
        for (int k=0, n=multipart.getCount(); k<n; k++) {
          	Part part = multipart.getBodyPart(k);
            MimeBodyPart mbp = (MimeBodyPart)part;

            if (mbp.isMimeType("text/html")) {
                log.debug("---------------> Handle html alternative. ");
                content += (String)part.getContent();
            }

          }
          
          return content;
	}

	private String handleMulitipart(Object o, String content, InboxEvent inboxentry) throws Exception{

		Multipart multipart = (Multipart) o;

		for (int k=0, n=multipart.getCount(); k<n; k++) {

          	Part part = multipart.getBodyPart(k);
            String disposition = part.getDisposition();
            MimeBodyPart mbp = (MimeBodyPart)part;

            if ((disposition != null) && (disposition.equals(Part.ATTACHMENT))){
              log.debug("---------------> Saving File "+part.getFileName()+" "+part.getContent());
              saveAttachment(part, inboxentry);

            }else{
              // Check if plain
              if (mbp.isMimeType("text/plain")) {
                log.debug("---------------> Handle plain. ");
                content += "<PRE style=\"font-size: 12px;\">" + (String)part.getContent() + "</PRE>";
                // Check if html
              } else if (mbp.isMimeType("text/html")) {
                log.debug("---------------> Handle plain. ");
                content += (String)part.getContent();
              } else {
                // Special non-attachment cases here of
                // image/gif, text/html, ...
                log.debug("---------------> Special non-attachment cases "+" "+part.getContentType());
				if ( mbp.isMimeType("multipart/*") ){
				  Object ob = part.getContent();
				  content = this.handleMulitipart(ob, content, inboxentry)+ "\n\n"  + content;
				}else{
	              saveAttachment(part, inboxentry);
				}
              }
            }
          }
          
          return content;
	}

	private void saveAttachment(Part part, InboxEvent inboxentry) throws Exception{
		
        MimeBodyPart mbp = (MimeBodyPart)part;
		String fileName= mbp.getFileName();
		String fileType= mbp.getContentType();
		String fileId= mbp.getContentID();
		String fileEncoding= mbp.getEncoding();
		String attContent;

		if (fileName==null || fileName.length()<2){
			fileName= new String("Unknown");
	      	if (fileType.indexOf("name")>0){
	      		int i = fileType.indexOf("name");
	      		int j = fileType.indexOf("\"", i+1);
	      		if (j!=-1){
	          		int k = fileType.indexOf("\"", j+1);
	          		if (k!=-1){
	          			fileName= fileType.substring(j+1, k);
	          		}
	          		
	      		} else {
	          		int k = fileType.indexOf(";", i+1);
	          		if (k!=-1){
	          			fileName= fileType.substring(i+5, k);
	              		
	          		}else{
	          			fileName= fileType.substring(i+5, fileType.length());
	          		}
	      		
	      		}
	      	}
		}
		
		InboxAttachment entryItem= new InboxAttachment();
		
		entryItem.setFileName(fileName);
		if (fileType!=null) entryItem.setContentType(fileType);

        if (mbp.getContent() instanceof InputStream){
        	InputStream is = new Base64.InputStream(mbp.getInputStream(), Base64.ENCODE);

        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        	StringBuffer att = new StringBuffer();
        	String thisLine=reader.readLine();

        	while (thisLine!=null) {
        	   att.append(thisLine);
        	   thisLine=reader.readLine();
        	}

        	attContent= att.toString();
//        	MimeUtility.encode(part.getOutputStream(), "base64");
//        	attachments += saveFile(part.getFileName(), part.getInputStream());
        	
        }else{
        	attContent = part.getContent().toString() ;
        }

		
		entryItem.setContent(attContent);
		entryItem.setContentId(fileId);
		
		inboxentry.addInboxAttachment(entryItem);
		
	}

	private String saveFile(String filename,InputStream stream){

    String pf = this.getClass().getResource("Pop3Job.class").getPath();
    pf = pf.substring(0, pf.indexOf("/cream"));
    String attachments_url = "/cream/attachments/";//TurbineResources.getString("attachments.web.location");
    String attachments_fs = pf+attachments_url;//TurbineResources.getString("attachments.fs.location");
    attachments_fs =  java.net.URLDecoder.decode(attachments_fs);


    String url = "";
    File file = new File(attachments_fs+filename);

      for (int i=0; file.exists(); i++) {
        file = new File(attachments_fs+i+filename);
        filename = i+filename;
      }

      try {
        file.createNewFile();
      } catch (IOException ex1) {
      	ex1.printStackTrace();
        return "";
      }

      log.debug("----- FILE CREATED "+file.exists()+" "+file.getAbsolutePath()+" "+file.canWrite());

      url = "<a href='"+attachments_url+filename+"' target='_blank'>"+filename+"</a><br>";

      try {
        FileOutputStream FOS = new FileOutputStream(file);

        byte b[] = new byte[16 * 1024];
        for (; ; ) {
          int bytes = stream.read(b);
          if (bytes < 0) {
            break;
          }
          if (bytes > 0) {
            FOS.write(b, 0, bytes);
          }
        }
        FOS.close();
        stream.close();
      } catch (Exception ex) {
        ex.printStackTrace();
        return "";
      }

      return url;
	}


	private String getTempCode()
	{
		Date currDate= new Date();
		return Integer.toString(currDate.hashCode());
	}

	private String getRowCode(String s, int i)
	{
		String is= new String();

		is= Integer.toString(i);
		while (is.length()<7)
		{
			is="0" + is;
		}

		is= s + is;
		return is;
	}

	private String formatDate(Date d)
	{
		SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");
		return formatter.format(d);
	}


}

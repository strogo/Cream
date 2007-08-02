package org.campware.cream.om;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.turbine.om.Retrievable;
import org.apache.torque.TorqueException;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;


/**
 * This class was autogenerated by Torque on:
 *
 * [Fri Jan 26 03:28:50 CET 2007]
 *
 * You should not use this class directly.  It should not even be
 * extended all references should be to ContactDoc
 */
public abstract class BaseContactDoc extends BaseObject
    implements org.apache.turbine.om.Retrievable
{
    /** The Peer class */
    private static final ContactDocPeer peer =
        new ContactDocPeer();

        
    /** The value for the docId field */
    private int docId;
      
    /** The value for the docType field */
    private int docType;
      
    /** The value for the docCode field */
    private int docCode;
      
    /** The value for the status field */
    private int status;
      
    /** The value for the subject field */
    private String subject;
      
    /** The value for the issuedDate field */
    private Date issuedDate;
      
    /** The value for the contactId field */
    private int contactId;
      
    /** The value for the created field */
    private Date created;
  
    
    /**
     * Get the DocId
     *
     * @return int
     */
    public int getDocId()
    {
        return docId;
    }

                        
    /**
     * Set the value of DocId
     *
     * @param v new value
     */
    public void setDocId(int v) 
    {
    
                  if (this.docId != v)
              {
            this.docId = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the DocType
     *
     * @return int
     */
    public int getDocType()
    {
        return docType;
    }

                        
    /**
     * Set the value of DocType
     *
     * @param v new value
     */
    public void setDocType(int v) 
    {
    
                  if (this.docType != v)
              {
            this.docType = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the DocCode
     *
     * @return int
     */
    public int getDocCode()
    {
        return docCode;
    }

                        
    /**
     * Set the value of DocCode
     *
     * @param v new value
     */
    public void setDocCode(int v) 
    {
    
                  if (this.docCode != v)
              {
            this.docCode = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the Status
     *
     * @return int
     */
    public int getStatus()
    {
        return status;
    }

                        
    /**
     * Set the value of Status
     *
     * @param v new value
     */
    public void setStatus(int v) 
    {
    
                  if (this.status != v)
              {
            this.status = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the Subject
     *
     * @return String
     */
    public String getSubject()
    {
        return subject;
    }

                        
    /**
     * Set the value of Subject
     *
     * @param v new value
     */
    public void setSubject(String v) 
    {
    
                  if (!ObjectUtils.equals(this.subject, v))
              {
            this.subject = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the IssuedDate
     *
     * @return Date
     */
    public Date getIssuedDate()
    {
        return issuedDate;
    }

                        
    /**
     * Set the value of IssuedDate
     *
     * @param v new value
     */
    public void setIssuedDate(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.issuedDate, v))
              {
            this.issuedDate = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ContactId
     *
     * @return int
     */
    public int getContactId()
    {
        return contactId;
    }

                        
    /**
     * Set the value of ContactId
     *
     * @param v new value
     */
    public void setContactId(int v) 
    {
    
                  if (this.contactId != v)
              {
            this.contactId = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the Created
     *
     * @return Date
     */
    public Date getCreated()
    {
        return created;
    }

                        
    /**
     * Set the value of Created
     *
     * @param v new value
     */
    public void setCreated(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.created, v))
              {
            this.created = v;
            setModified(true);
        }
    
          
              }
  
         
                
    private static List fieldNames = null;

    /**
     * Generate a list of field names.
     *
     * @return a list of field names
     */
    public static synchronized List getFieldNames()
    {
        if (fieldNames == null)
        {
            fieldNames = new ArrayList();
              fieldNames.add("DocId");
              fieldNames.add("DocType");
              fieldNames.add("DocCode");
              fieldNames.add("Status");
              fieldNames.add("Subject");
              fieldNames.add("IssuedDate");
              fieldNames.add("ContactId");
              fieldNames.add("Created");
              fieldNames = Collections.unmodifiableList(fieldNames);
        }
        return fieldNames;
    }

    /**
     * Retrieves a field from the object by name passed in as a String.
     *
     * @param name field name
     * @return value
     */
    public Object getByName(String name)
    {
          if (name.equals("DocId"))
        {
                return new Integer(getDocId());
            }
          if (name.equals("DocType"))
        {
                return new Integer(getDocType());
            }
          if (name.equals("DocCode"))
        {
                return new Integer(getDocCode());
            }
          if (name.equals("Status"))
        {
                return new Integer(getStatus());
            }
          if (name.equals("Subject"))
        {
                return getSubject();
            }
          if (name.equals("IssuedDate"))
        {
                return getIssuedDate();
            }
          if (name.equals("ContactId"))
        {
                return new Integer(getContactId());
            }
          if (name.equals("Created"))
        {
                return getCreated();
            }
          return null;
    }
    
    /**
     * Retrieves a field from the object by name passed in
     * as a String.  The String must be one of the static
     * Strings defined in this Class' Peer.
     *
     * @param name peer name
     * @return value
     */
    public Object getByPeerName(String name)
    {
          if (name.equals(ContactDocPeer.DOC_ID))
        {
                return new Integer(getDocId());
            }
          if (name.equals(ContactDocPeer.DOC_TYPE))
        {
                return new Integer(getDocType());
            }
          if (name.equals(ContactDocPeer.DOC_CODE))
        {
                return new Integer(getDocCode());
            }
          if (name.equals(ContactDocPeer.STATUS))
        {
                return new Integer(getStatus());
            }
          if (name.equals(ContactDocPeer.SUBJECT))
        {
                return getSubject();
            }
          if (name.equals(ContactDocPeer.ISSUED_DATE))
        {
                return getIssuedDate();
            }
          if (name.equals(ContactDocPeer.CONTACT_ID))
        {
                return new Integer(getContactId());
            }
          if (name.equals(ContactDocPeer.CREATED))
        {
                return getCreated();
            }
          return null;
    }

    /**
     * Retrieves a field from the object by Position as specified
     * in the xml schema.  Zero-based.
     *
     * @param pos position in xml schema
     * @return value
     */
    public Object getByPosition(int pos)
    {
            if (pos == 0)
        {
                return new Integer(getDocId());
            }
              if (pos == 1)
        {
                return new Integer(getDocType());
            }
              if (pos == 2)
        {
                return new Integer(getDocCode());
            }
              if (pos == 3)
        {
                return new Integer(getStatus());
            }
              if (pos == 4)
        {
                return getSubject();
            }
              if (pos == 5)
        {
                return getIssuedDate();
            }
              if (pos == 6)
        {
                return new Integer(getContactId());
            }
              if (pos == 7)
        {
                return getCreated();
            }
              return null;
    }
     
    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
          save(ContactDocPeer.getMapBuilder()
                .getDatabaseMap().getName());
      }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
       * Note: this code is here because the method body is
     * auto-generated conditionally and therefore needs to be
     * in this file instead of in the super class, BaseObject.
       *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
          try
        {
            con = Transaction.begin(dbName);
            save(con);
            Transaction.commit(con);
        }
        catch(TorqueException e)
        {
            Transaction.safeRollback(con);
            throw e;
        }
      }

      /** flag to prevent endless save loop, if this object is referenced
        by another object which falls in this transaction. */
    private boolean alreadyInSave = false;
      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
          if (!alreadyInSave)
        {
            alreadyInSave = true;


  
            // If this object has been modified, then save it to the database.
            if (isModified())
            {
                if (isNew())
                {
                    ContactDocPeer.doInsert((ContactDoc) this, con);
                    setNew(false);
                }
                else
                {
                    ContactDocPeer.doUpdate((ContactDoc) this, con);
                }
            }

                      alreadyInSave = false;
        }
      }

  
  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return null;
      }
 
    /**
     * get an id that differentiates this object from others
     * of its class.
     */
    public String getQueryKey()
    {
        if (getPrimaryKey() == null)
        {
            return "";
        }
        else
        {
            return getPrimaryKey().toString();
        }
    }

    /**
     * set an id that differentiates this object from others
     * of its class.
     */
    public void setQueryKey(String key)
        throws TorqueException
    {
        setPrimaryKey(key);
    }

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       * It then fills all the association collections and sets the
     * related objects to isNew=true.
       */
      public ContactDoc copy() throws TorqueException
    {
        return copyInto(new ContactDoc());
    }
  
    protected ContactDoc copyInto(ContactDoc copyObj) throws TorqueException
    {
          copyObj.setDocId(docId);
          copyObj.setDocType(docType);
          copyObj.setDocCode(docCode);
          copyObj.setStatus(status);
          copyObj.setSubject(subject);
          copyObj.setIssuedDate(issuedDate);
          copyObj.setContactId(contactId);
          copyObj.setCreated(created);
  
                                                  
                return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public ContactDocPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("ContactDoc:\n");
        str.append("DocId = ")
               .append(getDocId())
             .append("\n");
        str.append("DocType = ")
               .append(getDocType())
             .append("\n");
        str.append("DocCode = ")
               .append(getDocCode())
             .append("\n");
        str.append("Status = ")
               .append(getStatus())
             .append("\n");
        str.append("Subject = ")
               .append(getSubject())
             .append("\n");
        str.append("IssuedDate = ")
               .append(getIssuedDate())
             .append("\n");
        str.append("ContactId = ")
               .append(getContactId())
             .append("\n");
        str.append("Created = ")
               .append(getCreated())
             .append("\n");
        return(str.toString());
    }
}

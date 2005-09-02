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
 * [Wed May 04 09:10:56 CEST 2005]
 *
 * You should not use this class directly.  It should not even be
 * extended all references should be to OutboxEvent
 */
public abstract class BaseOutboxEvent extends BaseObject
    implements org.apache.turbine.om.Retrievable
{
    /** The Peer class */
    private static final OutboxEventPeer peer =
        new OutboxEventPeer();

        
    /** The value for the outboxEventId field */
    private int outboxEventId;
                                                
    /** The value for the outboxEventCode field */
    private String outboxEventCode = "AUTO";
                                          
    /** The value for the status field */
    private int status = 30;
                                          
    /** The value for the priority field */
    private int priority = 30;
      
    /** The value for the issuedDate field */
    private Date issuedDate;
      
    /** The value for the closedDate field */
    private Date closedDate;
      
    /** The value for the sentTime field */
    private Date sentTime;
                                          
    /** The value for the eventChannel field */
    private int eventChannel = 10;
                                          
    /** The value for the eventType field */
    private int eventType = 20;
                                          
    /** The value for the emailFormat field */
    private int emailFormat = 10;
                                          
    /** The value for the customerId field */
    private int customerId = 1000;
                                          
    /** The value for the projectId field */
    private int projectId = 1000;
                                          
    /** The value for the productId field */
    private int productId = 1000;
      
    /** The value for the receiverTo field */
    private String receiverTo;
      
    /** The value for the receiverCc field */
    private String receiverCc;
      
    /** The value for the subject field */
    private String subject;
      
    /** The value for the body field */
    private String body;
      
    /** The value for the notes field */
    private String notes;
      
    /** The value for the created field */
    private Date created;
      
    /** The value for the modified field */
    private Date modified;
      
    /** The value for the createdBy field */
    private String createdBy;
      
    /** The value for the modifiedBy field */
    private String modifiedBy;
  
    
    /**
     * Get the OutboxEventId
     *
     * @return int
     */
    public int getOutboxEventId()
    {
        return outboxEventId;
    }

                        
    /**
     * Set the value of OutboxEventId
     *
     * @param v new value
     */
    public void setOutboxEventId(int v) 
    {
    
                  if (this.outboxEventId != v)
              {
            this.outboxEventId = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the OutboxEventCode
     *
     * @return String
     */
    public String getOutboxEventCode()
    {
        return outboxEventCode;
    }

                        
    /**
     * Set the value of OutboxEventCode
     *
     * @param v new value
     */
    public void setOutboxEventCode(String v) 
    {
    
                  if (!ObjectUtils.equals(this.outboxEventCode, v))
              {
            this.outboxEventCode = v;
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
     * Get the Priority
     *
     * @return int
     */
    public int getPriority()
    {
        return priority;
    }

                        
    /**
     * Set the value of Priority
     *
     * @param v new value
     */
    public void setPriority(int v) 
    {
    
                  if (this.priority != v)
              {
            this.priority = v;
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
     * Get the ClosedDate
     *
     * @return Date
     */
    public Date getClosedDate()
    {
        return closedDate;
    }

                        
    /**
     * Set the value of ClosedDate
     *
     * @param v new value
     */
    public void setClosedDate(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.closedDate, v))
              {
            this.closedDate = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the SentTime
     *
     * @return Date
     */
    public Date getSentTime()
    {
        return sentTime;
    }

                        
    /**
     * Set the value of SentTime
     *
     * @param v new value
     */
    public void setSentTime(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.sentTime, v))
              {
            this.sentTime = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the EventChannel
     *
     * @return int
     */
    public int getEventChannel()
    {
        return eventChannel;
    }

                        
    /**
     * Set the value of EventChannel
     *
     * @param v new value
     */
    public void setEventChannel(int v) 
    {
    
                  if (this.eventChannel != v)
              {
            this.eventChannel = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the EventType
     *
     * @return int
     */
    public int getEventType()
    {
        return eventType;
    }

                        
    /**
     * Set the value of EventType
     *
     * @param v new value
     */
    public void setEventType(int v) 
    {
    
                  if (this.eventType != v)
              {
            this.eventType = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the EmailFormat
     *
     * @return int
     */
    public int getEmailFormat()
    {
        return emailFormat;
    }

                        
    /**
     * Set the value of EmailFormat
     *
     * @param v new value
     */
    public void setEmailFormat(int v) 
    {
    
                  if (this.emailFormat != v)
              {
            this.emailFormat = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the CustomerId
     *
     * @return int
     */
    public int getCustomerId()
    {
        return customerId;
    }

                              
    /**
     * Set the value of CustomerId
     *
     * @param v new value
     */
    public void setCustomerId(int v) throws TorqueException
    {
    
                  if (this.customerId != v)
              {
            this.customerId = v;
            setModified(true);
        }
    
                          
                if (aCustomer != null && !(aCustomer.getCustomerId() == v))
                {
            aCustomer = null;
        }
      
              }
  
    /**
     * Get the ProjectId
     *
     * @return int
     */
    public int getProjectId()
    {
        return projectId;
    }

                              
    /**
     * Set the value of ProjectId
     *
     * @param v new value
     */
    public void setProjectId(int v) throws TorqueException
    {
    
                  if (this.projectId != v)
              {
            this.projectId = v;
            setModified(true);
        }
    
                          
                if (aProject != null && !(aProject.getProjectId() == v))
                {
            aProject = null;
        }
      
              }
  
    /**
     * Get the ProductId
     *
     * @return int
     */
    public int getProductId()
    {
        return productId;
    }

                              
    /**
     * Set the value of ProductId
     *
     * @param v new value
     */
    public void setProductId(int v) throws TorqueException
    {
    
                  if (this.productId != v)
              {
            this.productId = v;
            setModified(true);
        }
    
                          
                if (aProduct != null && !(aProduct.getProductId() == v))
                {
            aProduct = null;
        }
      
              }
  
    /**
     * Get the ReceiverTo
     *
     * @return String
     */
    public String getReceiverTo()
    {
        return receiverTo;
    }

                        
    /**
     * Set the value of ReceiverTo
     *
     * @param v new value
     */
    public void setReceiverTo(String v) 
    {
    
                  if (!ObjectUtils.equals(this.receiverTo, v))
              {
            this.receiverTo = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ReceiverCc
     *
     * @return String
     */
    public String getReceiverCc()
    {
        return receiverCc;
    }

                        
    /**
     * Set the value of ReceiverCc
     *
     * @param v new value
     */
    public void setReceiverCc(String v) 
    {
    
                  if (!ObjectUtils.equals(this.receiverCc, v))
              {
            this.receiverCc = v;
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
     * Get the Body
     *
     * @return String
     */
    public String getBody()
    {
        return body;
    }

                        
    /**
     * Set the value of Body
     *
     * @param v new value
     */
    public void setBody(String v) 
    {
    
                  if (!ObjectUtils.equals(this.body, v))
              {
            this.body = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the Notes
     *
     * @return String
     */
    public String getNotes()
    {
        return notes;
    }

                        
    /**
     * Set the value of Notes
     *
     * @param v new value
     */
    public void setNotes(String v) 
    {
    
                  if (!ObjectUtils.equals(this.notes, v))
              {
            this.notes = v;
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
  
    /**
     * Get the Modified
     *
     * @return Date
     */
    public Date getModified()
    {
        return modified;
    }

                        
    /**
     * Set the value of Modified
     *
     * @param v new value
     */
    public void setModified(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.modified, v))
              {
            this.modified = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the CreatedBy
     *
     * @return String
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

                        
    /**
     * Set the value of CreatedBy
     *
     * @param v new value
     */
    public void setCreatedBy(String v) 
    {
    
                  if (!ObjectUtils.equals(this.createdBy, v))
              {
            this.createdBy = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ModifiedBy
     *
     * @return String
     */
    public String getModifiedBy()
    {
        return modifiedBy;
    }

                        
    /**
     * Set the value of ModifiedBy
     *
     * @param v new value
     */
    public void setModifiedBy(String v) 
    {
    
                  if (!ObjectUtils.equals(this.modifiedBy, v))
              {
            this.modifiedBy = v;
            setModified(true);
        }
    
          
              }
  
      
    
                  
    
        private Customer aCustomer;

    /**
     * Declares an association between this object and a Customer object
     *
     * @param v Customer
     * @throws TorqueException
     */
    public void setCustomer(Customer v) throws TorqueException
    {
            if (v == null)
        {
                          setCustomerId( 1000);
              }
        else
        {
            setCustomerId(v.getCustomerId());
        }
            aCustomer = v;
    }

                                            
    /**
     * Get the associated Customer object
     *
     * @return the associated Customer object
     * @throws TorqueException
     */
    public Customer getCustomer() throws TorqueException
    {
        if (aCustomer == null && (this.customerId != 0))
        {
                          aCustomer = CustomerPeer.retrieveByPK(SimpleKey.keyFor(this.customerId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Customer obj = CustomerPeer.retrieveByPK(this.customerId);
               obj.addOutboxEvents(this);
            */
        }
        return aCustomer;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setCustomerKey(ObjectKey key) throws TorqueException
    {
      
                        setCustomerId(((NumberKey) key).intValue());
                  }
    
    
                  
    
        private Project aProject;

    /**
     * Declares an association between this object and a Project object
     *
     * @param v Project
     * @throws TorqueException
     */
    public void setProject(Project v) throws TorqueException
    {
            if (v == null)
        {
                          setProjectId( 1000);
              }
        else
        {
            setProjectId(v.getProjectId());
        }
            aProject = v;
    }

                                            
    /**
     * Get the associated Project object
     *
     * @return the associated Project object
     * @throws TorqueException
     */
    public Project getProject() throws TorqueException
    {
        if (aProject == null && (this.projectId != 0))
        {
                          aProject = ProjectPeer.retrieveByPK(SimpleKey.keyFor(this.projectId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Project obj = ProjectPeer.retrieveByPK(this.projectId);
               obj.addOutboxEvents(this);
            */
        }
        return aProject;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setProjectKey(ObjectKey key) throws TorqueException
    {
      
                        setProjectId(((NumberKey) key).intValue());
                  }
    
    
                  
    
        private Product aProduct;

    /**
     * Declares an association between this object and a Product object
     *
     * @param v Product
     * @throws TorqueException
     */
    public void setProduct(Product v) throws TorqueException
    {
            if (v == null)
        {
                          setProductId( 1000);
              }
        else
        {
            setProductId(v.getProductId());
        }
            aProduct = v;
    }

                                            
    /**
     * Get the associated Product object
     *
     * @return the associated Product object
     * @throws TorqueException
     */
    public Product getProduct() throws TorqueException
    {
        if (aProduct == null && (this.productId != 0))
        {
                          aProduct = ProductPeer.retrieveByPK(SimpleKey.keyFor(this.productId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Product obj = ProductPeer.retrieveByPK(this.productId);
               obj.addOutboxEvents(this);
            */
        }
        return aProduct;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setProductKey(ObjectKey key) throws TorqueException
    {
      
                        setProductId(((NumberKey) key).intValue());
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
              fieldNames.add("OutboxEventId");
              fieldNames.add("OutboxEventCode");
              fieldNames.add("Status");
              fieldNames.add("Priority");
              fieldNames.add("IssuedDate");
              fieldNames.add("ClosedDate");
              fieldNames.add("SentTime");
              fieldNames.add("EventChannel");
              fieldNames.add("EventType");
              fieldNames.add("EmailFormat");
              fieldNames.add("CustomerId");
              fieldNames.add("ProjectId");
              fieldNames.add("ProductId");
              fieldNames.add("ReceiverTo");
              fieldNames.add("ReceiverCc");
              fieldNames.add("Subject");
              fieldNames.add("Body");
              fieldNames.add("Notes");
              fieldNames.add("Created");
              fieldNames.add("Modified");
              fieldNames.add("CreatedBy");
              fieldNames.add("ModifiedBy");
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
          if (name.equals("OutboxEventId"))
        {
                return new Integer(getOutboxEventId());
            }
          if (name.equals("OutboxEventCode"))
        {
                return getOutboxEventCode();
            }
          if (name.equals("Status"))
        {
                return new Integer(getStatus());
            }
          if (name.equals("Priority"))
        {
                return new Integer(getPriority());
            }
          if (name.equals("IssuedDate"))
        {
                return getIssuedDate();
            }
          if (name.equals("ClosedDate"))
        {
                return getClosedDate();
            }
          if (name.equals("SentTime"))
        {
                return getSentTime();
            }
          if (name.equals("EventChannel"))
        {
                return new Integer(getEventChannel());
            }
          if (name.equals("EventType"))
        {
                return new Integer(getEventType());
            }
          if (name.equals("EmailFormat"))
        {
                return new Integer(getEmailFormat());
            }
          if (name.equals("CustomerId"))
        {
                return new Integer(getCustomerId());
            }
          if (name.equals("ProjectId"))
        {
                return new Integer(getProjectId());
            }
          if (name.equals("ProductId"))
        {
                return new Integer(getProductId());
            }
          if (name.equals("ReceiverTo"))
        {
                return getReceiverTo();
            }
          if (name.equals("ReceiverCc"))
        {
                return getReceiverCc();
            }
          if (name.equals("Subject"))
        {
                return getSubject();
            }
          if (name.equals("Body"))
        {
                return getBody();
            }
          if (name.equals("Notes"))
        {
                return getNotes();
            }
          if (name.equals("Created"))
        {
                return getCreated();
            }
          if (name.equals("Modified"))
        {
                return getModified();
            }
          if (name.equals("CreatedBy"))
        {
                return getCreatedBy();
            }
          if (name.equals("ModifiedBy"))
        {
                return getModifiedBy();
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
          if (name.equals(OutboxEventPeer.OUTBOX_EVENT_ID))
        {
                return new Integer(getOutboxEventId());
            }
          if (name.equals(OutboxEventPeer.OUTBOX_EVENT_CODE))
        {
                return getOutboxEventCode();
            }
          if (name.equals(OutboxEventPeer.STATUS))
        {
                return new Integer(getStatus());
            }
          if (name.equals(OutboxEventPeer.PRIORITY))
        {
                return new Integer(getPriority());
            }
          if (name.equals(OutboxEventPeer.ISSUED_DATE))
        {
                return getIssuedDate();
            }
          if (name.equals(OutboxEventPeer.CLOSED_DATE))
        {
                return getClosedDate();
            }
          if (name.equals(OutboxEventPeer.SENT_TIME))
        {
                return getSentTime();
            }
          if (name.equals(OutboxEventPeer.EVENT_CHANNEL))
        {
                return new Integer(getEventChannel());
            }
          if (name.equals(OutboxEventPeer.EVENT_TYPE))
        {
                return new Integer(getEventType());
            }
          if (name.equals(OutboxEventPeer.EMAIL_FORMAT))
        {
                return new Integer(getEmailFormat());
            }
          if (name.equals(OutboxEventPeer.CUSTOMER_ID))
        {
                return new Integer(getCustomerId());
            }
          if (name.equals(OutboxEventPeer.PROJECT_ID))
        {
                return new Integer(getProjectId());
            }
          if (name.equals(OutboxEventPeer.PRODUCT_ID))
        {
                return new Integer(getProductId());
            }
          if (name.equals(OutboxEventPeer.RECEIVER_TO))
        {
                return getReceiverTo();
            }
          if (name.equals(OutboxEventPeer.RECEIVER_CC))
        {
                return getReceiverCc();
            }
          if (name.equals(OutboxEventPeer.SUBJECT))
        {
                return getSubject();
            }
          if (name.equals(OutboxEventPeer.BODY))
        {
                return getBody();
            }
          if (name.equals(OutboxEventPeer.NOTES))
        {
                return getNotes();
            }
          if (name.equals(OutboxEventPeer.CREATED))
        {
                return getCreated();
            }
          if (name.equals(OutboxEventPeer.MODIFIED))
        {
                return getModified();
            }
          if (name.equals(OutboxEventPeer.CREATED_BY))
        {
                return getCreatedBy();
            }
          if (name.equals(OutboxEventPeer.MODIFIED_BY))
        {
                return getModifiedBy();
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
                return new Integer(getOutboxEventId());
            }
              if (pos == 1)
        {
                return getOutboxEventCode();
            }
              if (pos == 2)
        {
                return new Integer(getStatus());
            }
              if (pos == 3)
        {
                return new Integer(getPriority());
            }
              if (pos == 4)
        {
                return getIssuedDate();
            }
              if (pos == 5)
        {
                return getClosedDate();
            }
              if (pos == 6)
        {
                return getSentTime();
            }
              if (pos == 7)
        {
                return new Integer(getEventChannel());
            }
              if (pos == 8)
        {
                return new Integer(getEventType());
            }
              if (pos == 9)
        {
                return new Integer(getEmailFormat());
            }
              if (pos == 10)
        {
                return new Integer(getCustomerId());
            }
              if (pos == 11)
        {
                return new Integer(getProjectId());
            }
              if (pos == 12)
        {
                return new Integer(getProductId());
            }
              if (pos == 13)
        {
                return getReceiverTo();
            }
              if (pos == 14)
        {
                return getReceiverCc();
            }
              if (pos == 15)
        {
                return getSubject();
            }
              if (pos == 16)
        {
                return getBody();
            }
              if (pos == 17)
        {
                return getNotes();
            }
              if (pos == 18)
        {
                return getCreated();
            }
              if (pos == 19)
        {
                return getModified();
            }
              if (pos == 20)
        {
                return getCreatedBy();
            }
              if (pos == 21)
        {
                return getModifiedBy();
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
          save(OutboxEventPeer.getMapBuilder()
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
                    OutboxEventPeer.doInsert((OutboxEvent) this, con);
                    setNew(false);
                }
                else
                {
                    OutboxEventPeer.doUpdate((OutboxEvent) this, con);
                }
            }

                      alreadyInSave = false;
        }
      }

                  
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param key outboxEventId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setOutboxEventId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setOutboxEventId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getOutboxEventId());
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
      public OutboxEvent copy() throws TorqueException
    {
        return copyInto(new OutboxEvent());
    }
  
    protected OutboxEvent copyInto(OutboxEvent copyObj) throws TorqueException
    {
          copyObj.setOutboxEventId(outboxEventId);
          copyObj.setOutboxEventCode(outboxEventCode);
          copyObj.setStatus(status);
          copyObj.setPriority(priority);
          copyObj.setIssuedDate(issuedDate);
          copyObj.setClosedDate(closedDate);
          copyObj.setSentTime(sentTime);
          copyObj.setEventChannel(eventChannel);
          copyObj.setEventType(eventType);
          copyObj.setEmailFormat(emailFormat);
          copyObj.setCustomerId(customerId);
          copyObj.setProjectId(projectId);
          copyObj.setProductId(productId);
          copyObj.setReceiverTo(receiverTo);
          copyObj.setReceiverCc(receiverCc);
          copyObj.setSubject(subject);
          copyObj.setBody(body);
          copyObj.setNotes(notes);
          copyObj.setCreated(created);
          copyObj.setModified(modified);
          copyObj.setCreatedBy(createdBy);
          copyObj.setModifiedBy(modifiedBy);
  
                            copyObj.setOutboxEventId( 0);
                                                                                                                                          
                return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public OutboxEventPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("OutboxEvent:\n");
        str.append("OutboxEventId = ")
               .append(getOutboxEventId())
             .append("\n");
        str.append("OutboxEventCode = ")
               .append(getOutboxEventCode())
             .append("\n");
        str.append("Status = ")
               .append(getStatus())
             .append("\n");
        str.append("Priority = ")
               .append(getPriority())
             .append("\n");
        str.append("IssuedDate = ")
               .append(getIssuedDate())
             .append("\n");
        str.append("ClosedDate = ")
               .append(getClosedDate())
             .append("\n");
        str.append("SentTime = ")
               .append(getSentTime())
             .append("\n");
        str.append("EventChannel = ")
               .append(getEventChannel())
             .append("\n");
        str.append("EventType = ")
               .append(getEventType())
             .append("\n");
        str.append("EmailFormat = ")
               .append(getEmailFormat())
             .append("\n");
        str.append("CustomerId = ")
               .append(getCustomerId())
             .append("\n");
        str.append("ProjectId = ")
               .append(getProjectId())
             .append("\n");
        str.append("ProductId = ")
               .append(getProductId())
             .append("\n");
        str.append("ReceiverTo = ")
               .append(getReceiverTo())
             .append("\n");
        str.append("ReceiverCc = ")
               .append(getReceiverCc())
             .append("\n");
        str.append("Subject = ")
               .append(getSubject())
             .append("\n");
        str.append("Body = ")
               .append(getBody())
             .append("\n");
        str.append("Notes = ")
               .append(getNotes())
             .append("\n");
        str.append("Created = ")
               .append(getCreated())
             .append("\n");
        str.append("Modified = ")
               .append(getModified())
             .append("\n");
        str.append("CreatedBy = ")
               .append(getCreatedBy())
             .append("\n");
        str.append("ModifiedBy = ")
               .append(getModifiedBy())
             .append("\n");
        return(str.toString());
    }
}
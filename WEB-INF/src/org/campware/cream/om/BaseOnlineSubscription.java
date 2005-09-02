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
 * extended all references should be to OnlineSubscription
 */
public abstract class BaseOnlineSubscription extends BaseObject
    implements org.apache.turbine.om.Retrievable
{
    /** The Peer class */
    private static final OnlineSubscriptionPeer peer =
        new OnlineSubscriptionPeer();

        
    /** The value for the onlineSubsId field */
    private int onlineSubsId;
                                                
    /** The value for the onlineSubsCode field */
    private String onlineSubsCode = "AUTO";
                                          
    /** The value for the status field */
    private int status = 30;
                                          
    /** The value for the priority field */
    private int priority = 30;
      
    /** The value for the issuedDate field */
    private Date issuedDate;
      
    /** The value for the closedDate field */
    private Date closedDate;
                                          
    /** The value for the sorderId field */
    private int sorderId = 1000;
                                          
    /** The value for the customerId field */
    private int customerId = 1000;
                                          
    /** The value for the recipientId field */
    private int recipientId = 1000;
                                          
    /** The value for the projectId field */
    private int projectId = 1000;
                                          
    /** The value for the productId field */
    private int productId = 1000;
                                          
    /** The value for the quantity field */
    private int quantity = 1;
      
    /** The value for the startDate field */
    private Date startDate;
      
    /** The value for the endDate field */
    private Date endDate;
                                                
    /** The value for the subject field */
    private String subject = "---";
      
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
     * Get the OnlineSubsId
     *
     * @return int
     */
    public int getOnlineSubsId()
    {
        return onlineSubsId;
    }

                        
    /**
     * Set the value of OnlineSubsId
     *
     * @param v new value
     */
    public void setOnlineSubsId(int v) 
    {
    
                  if (this.onlineSubsId != v)
              {
            this.onlineSubsId = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the OnlineSubsCode
     *
     * @return String
     */
    public String getOnlineSubsCode()
    {
        return onlineSubsCode;
    }

                        
    /**
     * Set the value of OnlineSubsCode
     *
     * @param v new value
     */
    public void setOnlineSubsCode(String v) 
    {
    
                  if (!ObjectUtils.equals(this.onlineSubsCode, v))
              {
            this.onlineSubsCode = v;
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
     * Get the SorderId
     *
     * @return int
     */
    public int getSorderId()
    {
        return sorderId;
    }

                              
    /**
     * Set the value of SorderId
     *
     * @param v new value
     */
    public void setSorderId(int v) throws TorqueException
    {
    
                  if (this.sorderId != v)
              {
            this.sorderId = v;
            setModified(true);
        }
    
                          
                if (aSorder != null && !(aSorder.getSorderId() == v))
                {
            aSorder = null;
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
    
                                                                  
                if (aCustomerRelatedByCustomerId != null && !(aCustomerRelatedByCustomerId.getCustomerId() == v))
                {
            aCustomerRelatedByCustomerId = null;
        }
      
              }
  
    /**
     * Get the RecipientId
     *
     * @return int
     */
    public int getRecipientId()
    {
        return recipientId;
    }

                              
    /**
     * Set the value of RecipientId
     *
     * @param v new value
     */
    public void setRecipientId(int v) throws TorqueException
    {
    
                  if (this.recipientId != v)
              {
            this.recipientId = v;
            setModified(true);
        }
    
                                                                  
                if (aCustomerRelatedByRecipientId != null && !(aCustomerRelatedByRecipientId.getCustomerId() == v))
                {
            aCustomerRelatedByRecipientId = null;
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
     * Get the Quantity
     *
     * @return int
     */
    public int getQuantity()
    {
        return quantity;
    }

                        
    /**
     * Set the value of Quantity
     *
     * @param v new value
     */
    public void setQuantity(int v) 
    {
    
                  if (this.quantity != v)
              {
            this.quantity = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the StartDate
     *
     * @return Date
     */
    public Date getStartDate()
    {
        return startDate;
    }

                        
    /**
     * Set the value of StartDate
     *
     * @param v new value
     */
    public void setStartDate(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.startDate, v))
              {
            this.startDate = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the EndDate
     *
     * @return Date
     */
    public Date getEndDate()
    {
        return endDate;
    }

                        
    /**
     * Set the value of EndDate
     *
     * @param v new value
     */
    public void setEndDate(Date v) 
    {
    
                  if (!ObjectUtils.equals(this.endDate, v))
              {
            this.endDate = v;
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
  
      
    
                        
        
        private Customer aCustomerRelatedByCustomerId;

    /**
     * Declares an association between this object and a Customer object
     *
     * @param v Customer
     * @throws TorqueException
     */
    public void setCustomerRelatedByCustomerId(Customer v) throws TorqueException
    {
            if (v == null)
        {
                          setCustomerId( 1000);
              }
        else
        {
            setCustomerId(v.getCustomerId());
        }
            aCustomerRelatedByCustomerId = v;
    }

                                            
    /**
     * Get the associated Customer object
     *
     * @return the associated Customer object
     * @throws TorqueException
     */
    public Customer getCustomerRelatedByCustomerId() throws TorqueException
    {
        if (aCustomerRelatedByCustomerId == null && (this.customerId != 0))
        {
                          aCustomerRelatedByCustomerId = CustomerPeer.retrieveByPK(SimpleKey.keyFor(this.customerId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Customer obj = CustomerPeer.retrieveByPK(this.customerId);
               obj.addOnlineSubscriptionsRelatedByCustomerId(this);
            */
        }
        return aCustomerRelatedByCustomerId;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setCustomerRelatedByCustomerIdKey(ObjectKey key) throws TorqueException
    {
      
                        setCustomerId(((NumberKey) key).intValue());
                  }
    
    
                        
        
        private Customer aCustomerRelatedByRecipientId;

    /**
     * Declares an association between this object and a Customer object
     *
     * @param v Customer
     * @throws TorqueException
     */
    public void setCustomerRelatedByRecipientId(Customer v) throws TorqueException
    {
            if (v == null)
        {
                          setRecipientId( 1000);
              }
        else
        {
            setRecipientId(v.getCustomerId());
        }
            aCustomerRelatedByRecipientId = v;
    }

                                            
    /**
     * Get the associated Customer object
     *
     * @return the associated Customer object
     * @throws TorqueException
     */
    public Customer getCustomerRelatedByRecipientId() throws TorqueException
    {
        if (aCustomerRelatedByRecipientId == null && (this.recipientId != 0))
        {
                          aCustomerRelatedByRecipientId = CustomerPeer.retrieveByPK(SimpleKey.keyFor(this.recipientId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Customer obj = CustomerPeer.retrieveByPK(this.recipientId);
               obj.addOnlineSubscriptionsRelatedByRecipientId(this);
            */
        }
        return aCustomerRelatedByRecipientId;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setCustomerRelatedByRecipientIdKey(ObjectKey key) throws TorqueException
    {
      
                        setRecipientId(((NumberKey) key).intValue());
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
               obj.addOnlineSubscriptions(this);
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
               obj.addOnlineSubscriptions(this);
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
    
    
                  
    
        private Sorder aSorder;

    /**
     * Declares an association between this object and a Sorder object
     *
     * @param v Sorder
     * @throws TorqueException
     */
    public void setSorder(Sorder v) throws TorqueException
    {
            if (v == null)
        {
                          setSorderId( 1000);
              }
        else
        {
            setSorderId(v.getSorderId());
        }
            aSorder = v;
    }

                                            
    /**
     * Get the associated Sorder object
     *
     * @return the associated Sorder object
     * @throws TorqueException
     */
    public Sorder getSorder() throws TorqueException
    {
        if (aSorder == null && (this.sorderId != 0))
        {
                          aSorder = SorderPeer.retrieveByPK(SimpleKey.keyFor(this.sorderId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Sorder obj = SorderPeer.retrieveByPK(this.sorderId);
               obj.addOnlineSubscriptions(this);
            */
        }
        return aSorder;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setSorderKey(ObjectKey key) throws TorqueException
    {
      
                        setSorderId(((NumberKey) key).intValue());
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
              fieldNames.add("OnlineSubsId");
              fieldNames.add("OnlineSubsCode");
              fieldNames.add("Status");
              fieldNames.add("Priority");
              fieldNames.add("IssuedDate");
              fieldNames.add("ClosedDate");
              fieldNames.add("SorderId");
              fieldNames.add("CustomerId");
              fieldNames.add("RecipientId");
              fieldNames.add("ProjectId");
              fieldNames.add("ProductId");
              fieldNames.add("Quantity");
              fieldNames.add("StartDate");
              fieldNames.add("EndDate");
              fieldNames.add("Subject");
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
          if (name.equals("OnlineSubsId"))
        {
                return new Integer(getOnlineSubsId());
            }
          if (name.equals("OnlineSubsCode"))
        {
                return getOnlineSubsCode();
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
          if (name.equals("SorderId"))
        {
                return new Integer(getSorderId());
            }
          if (name.equals("CustomerId"))
        {
                return new Integer(getCustomerId());
            }
          if (name.equals("RecipientId"))
        {
                return new Integer(getRecipientId());
            }
          if (name.equals("ProjectId"))
        {
                return new Integer(getProjectId());
            }
          if (name.equals("ProductId"))
        {
                return new Integer(getProductId());
            }
          if (name.equals("Quantity"))
        {
                return new Integer(getQuantity());
            }
          if (name.equals("StartDate"))
        {
                return getStartDate();
            }
          if (name.equals("EndDate"))
        {
                return getEndDate();
            }
          if (name.equals("Subject"))
        {
                return getSubject();
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
          if (name.equals(OnlineSubscriptionPeer.ONLINE_SUBS_ID))
        {
                return new Integer(getOnlineSubsId());
            }
          if (name.equals(OnlineSubscriptionPeer.ONLINE_SUBS_CODE))
        {
                return getOnlineSubsCode();
            }
          if (name.equals(OnlineSubscriptionPeer.STATUS))
        {
                return new Integer(getStatus());
            }
          if (name.equals(OnlineSubscriptionPeer.PRIORITY))
        {
                return new Integer(getPriority());
            }
          if (name.equals(OnlineSubscriptionPeer.ISSUED_DATE))
        {
                return getIssuedDate();
            }
          if (name.equals(OnlineSubscriptionPeer.CLOSED_DATE))
        {
                return getClosedDate();
            }
          if (name.equals(OnlineSubscriptionPeer.SORDER_ID))
        {
                return new Integer(getSorderId());
            }
          if (name.equals(OnlineSubscriptionPeer.CUSTOMER_ID))
        {
                return new Integer(getCustomerId());
            }
          if (name.equals(OnlineSubscriptionPeer.RECIPIENT_ID))
        {
                return new Integer(getRecipientId());
            }
          if (name.equals(OnlineSubscriptionPeer.PROJECT_ID))
        {
                return new Integer(getProjectId());
            }
          if (name.equals(OnlineSubscriptionPeer.PRODUCT_ID))
        {
                return new Integer(getProductId());
            }
          if (name.equals(OnlineSubscriptionPeer.QUANTITY))
        {
                return new Integer(getQuantity());
            }
          if (name.equals(OnlineSubscriptionPeer.START_DATE))
        {
                return getStartDate();
            }
          if (name.equals(OnlineSubscriptionPeer.END_DATE))
        {
                return getEndDate();
            }
          if (name.equals(OnlineSubscriptionPeer.SUBJECT))
        {
                return getSubject();
            }
          if (name.equals(OnlineSubscriptionPeer.NOTES))
        {
                return getNotes();
            }
          if (name.equals(OnlineSubscriptionPeer.CREATED))
        {
                return getCreated();
            }
          if (name.equals(OnlineSubscriptionPeer.MODIFIED))
        {
                return getModified();
            }
          if (name.equals(OnlineSubscriptionPeer.CREATED_BY))
        {
                return getCreatedBy();
            }
          if (name.equals(OnlineSubscriptionPeer.MODIFIED_BY))
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
                return new Integer(getOnlineSubsId());
            }
              if (pos == 1)
        {
                return getOnlineSubsCode();
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
                return new Integer(getSorderId());
            }
              if (pos == 7)
        {
                return new Integer(getCustomerId());
            }
              if (pos == 8)
        {
                return new Integer(getRecipientId());
            }
              if (pos == 9)
        {
                return new Integer(getProjectId());
            }
              if (pos == 10)
        {
                return new Integer(getProductId());
            }
              if (pos == 11)
        {
                return new Integer(getQuantity());
            }
              if (pos == 12)
        {
                return getStartDate();
            }
              if (pos == 13)
        {
                return getEndDate();
            }
              if (pos == 14)
        {
                return getSubject();
            }
              if (pos == 15)
        {
                return getNotes();
            }
              if (pos == 16)
        {
                return getCreated();
            }
              if (pos == 17)
        {
                return getModified();
            }
              if (pos == 18)
        {
                return getCreatedBy();
            }
              if (pos == 19)
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
          save(OnlineSubscriptionPeer.getMapBuilder()
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
                    OnlineSubscriptionPeer.doInsert((OnlineSubscription) this, con);
                    setNew(false);
                }
                else
                {
                    OnlineSubscriptionPeer.doUpdate((OnlineSubscription) this, con);
                }
            }

                      alreadyInSave = false;
        }
      }

                  
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param key onlineSubsId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setOnlineSubsId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setOnlineSubsId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getOnlineSubsId());
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
      public OnlineSubscription copy() throws TorqueException
    {
        return copyInto(new OnlineSubscription());
    }
  
    protected OnlineSubscription copyInto(OnlineSubscription copyObj) throws TorqueException
    {
          copyObj.setOnlineSubsId(onlineSubsId);
          copyObj.setOnlineSubsCode(onlineSubsCode);
          copyObj.setStatus(status);
          copyObj.setPriority(priority);
          copyObj.setIssuedDate(issuedDate);
          copyObj.setClosedDate(closedDate);
          copyObj.setSorderId(sorderId);
          copyObj.setCustomerId(customerId);
          copyObj.setRecipientId(recipientId);
          copyObj.setProjectId(projectId);
          copyObj.setProductId(productId);
          copyObj.setQuantity(quantity);
          copyObj.setStartDate(startDate);
          copyObj.setEndDate(endDate);
          copyObj.setSubject(subject);
          copyObj.setNotes(notes);
          copyObj.setCreated(created);
          copyObj.setModified(modified);
          copyObj.setCreatedBy(createdBy);
          copyObj.setModifiedBy(modifiedBy);
  
                            copyObj.setOnlineSubsId( 0);
                                                                                                                              
                return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public OnlineSubscriptionPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("OnlineSubscription:\n");
        str.append("OnlineSubsId = ")
               .append(getOnlineSubsId())
             .append("\n");
        str.append("OnlineSubsCode = ")
               .append(getOnlineSubsCode())
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
        str.append("SorderId = ")
               .append(getSorderId())
             .append("\n");
        str.append("CustomerId = ")
               .append(getCustomerId())
             .append("\n");
        str.append("RecipientId = ")
               .append(getRecipientId())
             .append("\n");
        str.append("ProjectId = ")
               .append(getProjectId())
             .append("\n");
        str.append("ProductId = ")
               .append(getProductId())
             .append("\n");
        str.append("Quantity = ")
               .append(getQuantity())
             .append("\n");
        str.append("StartDate = ")
               .append(getStartDate())
             .append("\n");
        str.append("EndDate = ")
               .append(getEndDate())
             .append("\n");
        str.append("Subject = ")
               .append(getSubject())
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

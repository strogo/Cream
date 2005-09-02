package org.campware.cream.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  *  This class was autogenerated by Torque on:
  *
  * [Wed May 04 09:10:56 CEST 2005]
  *
  */
public class NewsletterMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.NewsletterMapBuilder";


    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("cream");

        dbMap.addTable("NEWSLETTER");
        TableMap tMap = dbMap.getTable("NEWSLETTER");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);

        tMap.setPrimaryKeyMethodInfo("NEWSLETTER_SEQ");

              tMap.addPrimaryKey("NEWSLETTER.NEWSLETTER_ID", new Integer(0));
                    tMap.addColumn("NEWSLETTER.NEWSLETTER_CODE", "");
                    tMap.addColumn("NEWSLETTER.STATUS", new Integer(0));
                    tMap.addColumn("NEWSLETTER.PRIORITY", new Integer(0));
                    tMap.addColumn("NEWSLETTER.ISSUED_DATE", new Date());
                    tMap.addColumn("NEWSLETTER.CLOSED_DATE", new Date());
                    tMap.addColumn("NEWSLETTER.SENT_TIME", new Date());
                    tMap.addColumn("NEWSLETTER.EMAIL_FORMAT", new Integer(0));
                    tMap.addForeignKey(
                "NEWSLETTER.LANGUAGE_ID", new Integer(0) , "LANGUAGE" ,
                "LANGUAGE_ID");
                    tMap.addForeignKey(
                "NEWSLETTER.CUSTOMER_CAT_ID", new Integer(0) , "CUSTOMER_CATEGORY" ,
                "CUSTOMER_CAT_ID");
                    tMap.addColumn("NEWSLETTER.CUSTOMER_TYPE", new Integer(0));
                    tMap.addForeignKey(
                "NEWSLETTER.CUST_LANGUAGE_ID", new Integer(0) , "LANGUAGE" ,
                "LANGUAGE_ID");
                    tMap.addForeignKey(
                "NEWSLETTER.CUST_COUNTRY_ID", new Integer(0) , "COUNTRY" ,
                "COUNTRY_ID");
                    tMap.addColumn("NEWSLETTER.REL_DOCUMENT", new Integer(0));
                    tMap.addColumn("NEWSLETTER.REL_DOC_STATUS", new Integer(0));
                    tMap.addForeignKey(
                "NEWSLETTER.REL_PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "NEWSLETTER.REL_PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                "PRODUCT_ID");
                    tMap.addForeignKey(
                "NEWSLETTER.PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "NEWSLETTER.PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                "PRODUCT_ID");
                    tMap.addColumn("NEWSLETTER.SUBJECT", "");
                    tMap.addColumn("NEWSLETTER.BODY", "");
                    tMap.addColumn("NEWSLETTER.NOTES", "");
                    tMap.addColumn("NEWSLETTER.CREATED", new Date());
                    tMap.addColumn("NEWSLETTER.MODIFIED", new Date());
                    tMap.addColumn("NEWSLETTER.CREATED_BY", "");
                    tMap.addColumn("NEWSLETTER.MODIFIED_BY", "");
          }
}

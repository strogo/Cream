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
  * [Fri Jan 26 03:28:50 CET 2007]
  *
  */
public class InboxEventMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.InboxEventMapBuilder";


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

        dbMap.addTable("INBOX_EVENT");
        TableMap tMap = dbMap.getTable("INBOX_EVENT");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);


              tMap.addPrimaryKey("INBOX_EVENT.INBOX_EVENT_ID", new Integer(0));
                    tMap.addColumn("INBOX_EVENT.INBOX_EVENT_CODE", "");
                    tMap.addColumn("INBOX_EVENT.STATUS", new Integer(0));
                    tMap.addColumn("INBOX_EVENT.PRIORITY", new Integer(0));
                    tMap.addColumn("INBOX_EVENT.ISSUED_DATE", new Date());
                    tMap.addColumn("INBOX_EVENT.CLOSED_DATE", new Date());
                    tMap.addColumn("INBOX_EVENT.EVENT_CHANNEL", new Integer(0));
                    tMap.addColumn("INBOX_EVENT.EVENT_TYPE", new Integer(0));
                    tMap.addColumn("INBOX_EVENT.EMAIL_FORMAT", new Integer(0));
                    tMap.addForeignKey(
                "INBOX_EVENT.CUSTOMER_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "INBOX_EVENT.CONTACT_ID", new Integer(0) , "CONTACT" ,
                "CONTACT_ID");
                    tMap.addForeignKey(
                "INBOX_EVENT.PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "INBOX_EVENT.PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                "PRODUCT_ID");
                    tMap.addColumn("INBOX_EVENT.SENDER_NAME", "");
                    tMap.addColumn("INBOX_EVENT.SENDER_EMAIL", "");
                    tMap.addColumn("INBOX_EVENT.SENDER_REPLY_TO", "");
                    tMap.addColumn("INBOX_EVENT.SENT_TIME", new Date());
                    tMap.addColumn("INBOX_EVENT.SENDER_TO", "");
                    tMap.addColumn("INBOX_EVENT.SENDER_CC", "");
                    tMap.addColumn("INBOX_EVENT.CUSTOM_1", "");
                    tMap.addColumn("INBOX_EVENT.CUSTOM_2", "");
                    tMap.addColumn("INBOX_EVENT.CUSTOM_3", "");
                    tMap.addColumn("INBOX_EVENT.CUSTOM_4", "");
                    tMap.addColumn("INBOX_EVENT.CUSTOM_5", "");
                    tMap.addColumn("INBOX_EVENT.CUSTOM_6", "");
                    tMap.addColumn("INBOX_EVENT.SUBJECT", "");
                    tMap.addColumn("INBOX_EVENT.BODY", "");
                    tMap.addColumn("INBOX_EVENT.NOTES", "");
                    tMap.addColumn("INBOX_EVENT.CREATED", new Date());
                    tMap.addColumn("INBOX_EVENT.MODIFIED", new Date());
                    tMap.addColumn("INBOX_EVENT.CREATED_BY", "");
                    tMap.addColumn("INBOX_EVENT.MODIFIED_BY", "");
          }
}

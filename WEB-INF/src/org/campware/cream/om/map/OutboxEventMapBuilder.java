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
public class OutboxEventMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.OutboxEventMapBuilder";


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

        dbMap.addTable("OUTBOX_EVENT");
        TableMap tMap = dbMap.getTable("OUTBOX_EVENT");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);

        tMap.setPrimaryKeyMethodInfo("OUTBOX_EVENT_SEQ");

              tMap.addPrimaryKey("OUTBOX_EVENT.OUTBOX_EVENT_ID", new Integer(0));
                    tMap.addColumn("OUTBOX_EVENT.OUTBOX_EVENT_CODE", "");
                    tMap.addColumn("OUTBOX_EVENT.STATUS", new Integer(0));
                    tMap.addColumn("OUTBOX_EVENT.PRIORITY", new Integer(0));
                    tMap.addColumn("OUTBOX_EVENT.ISSUED_DATE", new Date());
                    tMap.addColumn("OUTBOX_EVENT.CLOSED_DATE", new Date());
                    tMap.addColumn("OUTBOX_EVENT.SENT_TIME", new Date());
                    tMap.addColumn("OUTBOX_EVENT.EVENT_CHANNEL", new Integer(0));
                    tMap.addColumn("OUTBOX_EVENT.EVENT_TYPE", new Integer(0));
                    tMap.addColumn("OUTBOX_EVENT.EMAIL_FORMAT", new Integer(0));
                    tMap.addForeignKey(
                "OUTBOX_EVENT.CUSTOMER_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "OUTBOX_EVENT.PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "OUTBOX_EVENT.PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                "PRODUCT_ID");
                    tMap.addColumn("OUTBOX_EVENT.RECEIVER_TO", "");
                    tMap.addColumn("OUTBOX_EVENT.RECEIVER_CC", "");
                    tMap.addColumn("OUTBOX_EVENT.SUBJECT", "");
                    tMap.addColumn("OUTBOX_EVENT.BODY", "");
                    tMap.addColumn("OUTBOX_EVENT.NOTES", "");
                    tMap.addColumn("OUTBOX_EVENT.CREATED", new Date());
                    tMap.addColumn("OUTBOX_EVENT.MODIFIED", new Date());
                    tMap.addColumn("OUTBOX_EVENT.CREATED_BY", "");
                    tMap.addColumn("OUTBOX_EVENT.MODIFIED_BY", "");
          }
}

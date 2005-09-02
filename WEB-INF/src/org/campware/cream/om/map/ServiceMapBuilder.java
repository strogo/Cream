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
public class ServiceMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.ServiceMapBuilder";


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

        dbMap.addTable("SERVICE");
        TableMap tMap = dbMap.getTable("SERVICE");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);

        tMap.setPrimaryKeyMethodInfo("SERVICE_SEQ");

              tMap.addPrimaryKey("SERVICE.SERVICE_ID", new Integer(0));
                    tMap.addColumn("SERVICE.SERVICE_CODE", "");
                    tMap.addColumn("SERVICE.STATUS", new Integer(0));
                    tMap.addColumn("SERVICE.PRIORITY", new Integer(0));
                    tMap.addColumn("SERVICE.ISSUED_DATE", new Date());
                    tMap.addColumn("SERVICE.CLOSED_DATE", new Date());
                    tMap.addForeignKey(
                "SERVICE.CUSTOMER_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "SERVICE.RECIPIENT_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "SERVICE.PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "SERVICE.SORDER_ID", new Integer(0) , "SORDER" ,
                "SORDER_ID");
                    tMap.addColumn("SERVICE.INVOICE_CODE", "");
                    tMap.addColumn("SERVICE.SUBJECT", "");
                    tMap.addColumn("SERVICE.NOTES", "");
                    tMap.addColumn("SERVICE.CREATED", new Date());
                    tMap.addColumn("SERVICE.MODIFIED", new Date());
                    tMap.addColumn("SERVICE.CREATED_BY", "");
                    tMap.addColumn("SERVICE.MODIFIED_BY", "");
          }
}

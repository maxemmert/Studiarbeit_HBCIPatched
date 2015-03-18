package hbci4java.sepa;

import hbci4java.AbstractTestGV;

import org.junit.Assert;
import org.junit.Test;
import org.kapott.hbci.GV.HBCIJob;
import org.kapott.hbci.GV_Result.HBCIJobResult;
import org.kapott.hbci.status.HBCIExecStatus;
import org.kapott.hbci.structures.Konto;
import org.kapott.hbci.structures.Value;


public class TestGVTermUebSEPA extends AbstractTestGV {
    
    @Override
    @Test
    public void test() {
        System.out.println("---------Erstelle Job");
        HBCIJob job =  handler.newJob("TermUebSEPA");
        
        Konto acc = new Konto();
        acc.blz = params.getProperty("target_blz");
        acc.number = params.getProperty("target_number");
        acc.name = "Kurt Mustermann";
        acc.bic = params.getProperty("target_bic");
        acc.iban = params.getProperty("target_iban");
        
        int source_acc_idx = Integer.parseInt(params.getProperty("source_account_idx"));
        job.setParam("src",passport.getAccounts()[source_acc_idx]);
        job.setParam("dst",acc);
        
        String value = params.getProperty("value");
        if(value == null) value = "100";
        job.setParam("btg",new Value(Integer.parseInt(value),"EUR"));
        job.setParam("usage",params.getProperty("usage"));
        job.setParam("date", params.getProperty("date"));
        
        System.out.println("---------F�r Job zur Queue");
        job.addToQueue();

        
        HBCIExecStatus ret = handler.execute();
        HBCIJobResult res = job.getJobResult();
        System.out.println("----------Result: "+res.toString());
        
        Assert.assertEquals("Job Result ist nicht OK!", true, res.isOK());
    }

    @Override
    protected String getJobname()
    {
        return "TermUebSEPA";
    }

}

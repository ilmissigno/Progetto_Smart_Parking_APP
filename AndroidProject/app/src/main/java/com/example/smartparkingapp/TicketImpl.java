package com.example.smartparkingapp;

public class TicketImpl extends SkeletonTicket{

    public TicketImpl(){
    }

    @Override
    public void RiceviCostoTicket(double costoTicket) {
        AcquistaTicket acquistaTicket = new AcquistaTicket();
        acquistaTicket.setCostoTicket(costoTicket);
    }

    @Override
    public void ConfirmLogin(String auth) {
        MainActivity mainActivity = new MainActivity();
        mainActivity.setAuth(auth);

    }
}


package sr.ice.server;
// **********************************************************************
//
// Copyright (c) 2003-2019 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

import SmartHome.Garden.CameraBounds;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Identity;

import java.util.Scanner;

public class Server
{

	public void t1(String[] args)
	{
		int status = 0;
		Communicator communicator = null;

		try
		{
			// 1. Inicjalizacja ICE - utworzenie communicatora
			communicator = Util.initialize(args);

			// 2. Konfiguracja adaptera
			// METODA 1 (polecana produkcyjnie): Konfiguracja adaptera Adapter1 jest w pliku konfiguracyjnym podanym jako parametr uruchomienia serwera
			//Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");  
			
			// METODA 2 (niepolecana, dopuszczalna testowo): Konfiguracja adaptera Adapter1 jest w kodzie Ÿród³owym
			ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h 0.0.0.0 -p 10000:udp -h localhost -p 10000");

			MyServantLocator servantLocator = new MyServantLocator();
			adapter.addServantLocator(servantLocator, "camera");
			adapter.addServantLocator(servantLocator, "grassMower");
			adapter.addServantLocator(servantLocator, "speakers");
			adapter.addServantLocator(servantLocator, "television");
			adapter.addServantLocator(servantLocator, "coffeeExpress");
	        
			// 5. Aktywacja adaptera i przejœcie w pêtlê przetwarzania ¿¹dañ
			adapter.activate();
			
			System.out.println("Entering event processing loop...");
			Scanner s = new Scanner(System.in);
			while (s.hasNext("list")){
				s.next();
				for(String key : servantLocator.getDevices().keySet()){
					System.out.println(key);
				}
			}
			communicator.waitForShutdown();
		}
		catch (Exception e)
		{
			System.err.println(e);
			status = 1;
		}
		if (communicator != null)
		{
			// Clean up
			//
			try
			{
				communicator.destroy();
			}
			catch (Exception e)
			{
				System.err.println(e);
				status = 1;
			}
		}
		System.exit(status);
	}


	public static void main(String[] args)
	{
		Server app = new Server();
		app.t1(args);
	}
}

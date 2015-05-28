/**
Copyright (c) 2013, Giovanni Iacca (giovanniiaccca@incas3.eu)
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the FreeBSD Project.
*/

package unalcol.optimization.real.testbed.cec2013;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

//import com.sun.servicetag.SystemEnvironment;

/**
 * This class provides an utility function to dynamically load native libraries 
 * contained into a jar file.
 */
public class ClassLoaderHelper
{
	public static final ClassLoader loader = ClassLoaderHelper.class.getClassLoader();

	private static final boolean forceReload = true;
	
	public static void loadFolderFromJar(String path) throws Exception
	{
		InputStream in = null;
		FileOutputStream fos = null;

		try
		{
                    File dir = new File(path);
                    for (File nextFile : dir.listFiles())
                    {
                                in = new FileInputStream(nextFile.toString());

                                File temp = new File("cdatafiles" + File.separator + nextFile.getName());
                                temp.getParentFile().mkdirs(); 
                                temp.createNewFile();
                                fos = new FileOutputStream(temp);

                                byte[] buffer = new byte[1024];
                                int read = -1;

                                while ((read = in.read(buffer)) != -1)
                                        fos.write(buffer, 0, read);
                    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fos != null)
				fos.close();
			if (in != null)
				in.close();
		}
	}
	
	/**
	 * Loads a native library contained into a jar file.
	 * 
	 * @param name the name of the native library.
	 * @throws IOException
	 */
	public static void loadNativeLibraryFromJar(String name) throws Exception
	{
		boolean libraryLoaded = false;
		
		int trials = 600; // 600 x 100 ms = 60 seconds
		int i = 0;
		
		// build the platform-dependent name of the native library
		String architecture = "";
		String osName = "";
		String archLibDir = "";
		
		try
		{
                        osName = System.getProperty("os.name");
			architecture = System.getProperty("os.arch");
		}
		catch (NoClassDefFoundError e)
		{
			architecture = System.getProperty("os.arch");
			osName = System.getProperty("os.name");
		}
		
		if (osName.contains("Windows"))
		{		
			if (architecture.equalsIgnoreCase("x86") || architecture.equalsIgnoreCase("i386"))
				archLibDir = "win32";
			else
				archLibDir = "win64";

			name += ".dll";
		}
		else if (osName.contains("Mac"))
		{	
			if (architecture.equalsIgnoreCase("x86") || architecture.equalsIgnoreCase("i386"))
				archLibDir = "mac32";
			else
				archLibDir = "mac64";

			name += ".jnilib";
		}
		else
		{	
			if (architecture.equalsIgnoreCase("x86") || architecture.equalsIgnoreCase("i386"))
				archLibDir = "x86";
			else
				archLibDir = "x86_64";

			name += ".so.1.0.1";
		}
		
		String nameMangling;
		
		// hack: try multiple times to load the native library
		while (!libraryLoaded && i < trials)
		{
			// create a random mangling
			long time = System.currentTimeMillis();
			long random = new Random(time).nextLong();
			nameMangling = name + "-" + (long)(time + random);
			
			File temp = null;
			InputStream in = null;
			FileOutputStream fos = null;
			
			try
			{
				// create a temporary file to store the native library
				temp = new File(new File(System.getProperty("java.io.tmpdir")), nameMangling);
				// force its deletion at jvm shutdown
				temp.deleteOnExit();
				
				if (!temp.exists() || temp.length() == 0 || forceReload)
				{
                                    System.out.println("./lib" + File.separator + archLibDir + File.separator + name);
					// read the native library from jar file
					in = new FileInputStream("lib" + File.separator + archLibDir + File.separator + name);

					// save the native library to the temporary file
                                        System.out.println(temp);
					fos = new FileOutputStream(temp);
                                        System.out.println("temporal...");
					
					byte[] buffer = new byte[1024];
					int read = -1;

					while ((read = in.read(buffer)) != -1)
						fos.write(buffer, 0, read);
                                        System.out.println("done...");
				}
				
				// try to load the native library
				if (temp.exists() && temp.length() > 0)
				{
					try
					{
						System.load(temp.getAbsolutePath());
						libraryLoaded = true;
					}
					catch (UnsatisfiedLinkError ule)
					{
						libraryLoaded = false;
						// throw the exception only if the loader failed all the times
						if (i == trials)
							throw ule;
					}
				}
			}
			catch (Exception e)
			{
                            e.printStackTrace();
				libraryLoaded = false;
				// throw the exception only if the loader failed all the times
				if (i == trials)
					throw e;
			}
			finally
			{
				if (fos != null)
					fos.close();
				if (in != null)
					in.close();
			}
			
			try
			{
				//Thread.yield();
				Thread.sleep(100);
			}
			catch (Exception e) {}

			i++;
		}
	}
}
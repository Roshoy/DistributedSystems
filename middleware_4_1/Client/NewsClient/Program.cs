using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Grpc.Core;
using Tutorial;

namespace NewsClient
{
    class Program
    {
        private ClientName _name;
        private NewsProvider.NewsProviderClient _client;
        private List<Task> _subscriptions = new List<Task>(); 
        private List<Task> _newSubscriptions = new List<Task>();
        private bool _isAlive = true;
        private bool _serverUp = true;
        private bool _nowIsInput = true;

        public void Run()
        {
            Console.WriteLine("Enter your name");
            _name = new ClientName() { Name = Console.ReadLine() };
            Channel channel = new Channel("127.0.0.1:50051", ChannelCredentials.Insecure);
            _client = new NewsProvider.NewsProviderClient(channel);

            SubscribeToInput();
            var closingTask = CloseClient();
            while (_isAlive)
            {
                try
                {
                    var prevServerStatus = _serverUp;
                    CheckOnServer().Wait();
                    if(!prevServerStatus && _serverUp)
                    {
                        SubscribeToInput();
                    }
                }
                catch (Grpc.Core.RpcException e)
                {
                    Console.WriteLine($"While asking server for previous subscriptions: \n{e.Status.Detail}");
                }
                catch (Exception e)
                {
                    Console.WriteLine($"While asking server for previous subscriptions: \n{e.Message}:\n{e.InnerException.Message}");
                }
                
            }

            closingTask.Wait();

            CloseClientOnServerSide();

            WaitForSubscriptions();

            channel.ShutdownAsync().Wait();
        }

        private void SubscribeToInput()
        {
            _nowIsInput = true;
            var requests = GetAllRequestInput();
            _nowIsInput = false;

            foreach (var req in requests)
            {
                _subscriptions.Add(SubscribeAsync(req));
            }
        }

        private void WaitForSubscriptions()
        {
            foreach (var task in _subscriptions)
            {
                try
                {
                    task.Wait();
                }
                catch (Grpc.Core.RpcException e)
                {
                    Console.WriteLine($"While waiting on closing subscription stream: \n{e.Status.Detail}");
                }
                catch (Exception e)
                {
                    Console.WriteLine($"While waiting on closing subscription stream: \n{e.Message}:\n{e.InnerException.Message}");
                }
            }
        }

        private async Task CloseClient()
        {
            await Task.Factory.StartNew(() =>
                {
                    do
                        Console.ReadKey(true);
                    while (_nowIsInput);
                        
                });
            _isAlive = false;
            CloseClientOnServerSide();
        }

        private async Task CheckOnServer()
        {
            try
            {
                StartAllSubscriptions().Wait();
                _serverUp = true;
            }
            catch (Grpc.Core.RpcException e)
            {
                Console.WriteLine($"While checking up on server: \n{e.Status.Detail}");
                _serverUp = false;
            }
            catch (Exception e)
            {
                Console.WriteLine($"While checking up on server: \n{e.Message}:\n{e.InnerException.Message}");
                _serverUp = false;
            }
            await Task.Delay(3000);
        }

        private List<NewsRequest> GetAllRequestInput()
        {
            var requests = new List<NewsRequest>();

            while (true)
            {
                Console.WriteLine("Do you want to add new subscription? (escape if no)");

                if (Console.ReadKey(true).Key.Equals(ConsoleKey.Escape)) break;

                requests.Add( GetRequestInput() );
            }
            Console.WriteLine("To exit press any key");
            
            return requests;
        }

        private NewsRequest GetRequestInput()
        {
            Console.WriteLine("Enter search tag:");
            var tag = Console.ReadLine();
            ArticleType type;
            String typeStr;
            do
            {
                Console.WriteLine("Enter category:");
                typeStr = Console.ReadLine();
            } while (!Enum.TryParse(typeStr, true, out type));
            return new NewsRequest() { ClientId = _name, SearchingTag = tag, Type = type };
        }

        private void CloseClientOnServerSide()
        {
            try
            {
                _client.closeClient(_name);
            }
            catch (Grpc.Core.RpcException e)
            {
                Console.WriteLine($"\nWhile sending close client request to server: \n{e.Status.Detail}");
            }
            catch (Exception e)
            {
                Console.WriteLine($"\nWhile sending close client request to server: \n{e.Message}:\n{e.InnerException.Message}");
            }
        }

        private async Task StartAllSubscriptions()
        {
            var requests = new List<NewsRequest>();
            using (var call = _client.openClient(_name))
            {
                Console.WriteLine("Otwieram na serwerze");
                while (await call.ResponseStream.MoveNext())
                {
                    var response = call.ResponseStream.Current;
                    requests.Add(response);
                }
            }
            foreach(var r in requests)
            {
                Console.WriteLine("Old subscription:");
                _newSubscriptions.Add(SubscribeAsync(r));
            }
        }

        private async Task SubscribeAsync(NewsRequest request)
        {            
            using (var call = _client.subscribe(request))
            {
                Console.WriteLine($"Zasubskrybowano na {request.SearchingTag} w typie {request.Type}");
                while (await call.ResponseStream.MoveNext())
                {
                    var response = call.ResponseStream.Current;
                    PrintArticle(response);
                }
            }
            Console.WriteLine($"Subscription has ended on {request.SearchingTag} of type {request.Type}");
        }

        private void PrintArticle(NewsResponse response)
        {
            var sb = new StringBuilder();
            sb.Append("NEW ARTICLE!!!");
            sb.AppendLine();
            sb.Append(response.Title);
            sb.Append(" by ");
            sb.Append(response.Author);
            sb.AppendLine();
            sb.Append(response.Content);
            Console.WriteLine(sb);
        }

        static void Main(string[] args)
        {
            var program = new Program();
            program.Run();
            Console.ReadKey();
        }
    }
}

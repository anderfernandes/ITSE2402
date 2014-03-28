using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.Xml.Linq;

namespace SbSCh11_2
{
    public class StockQuote
    {
        public double last { get; set; }
        public DateTime date { get; set; }
        public DateTime time { get; set; }
        public double change { get; set; }
        public double open { get; set; }
        public double high { get; set; }
        public double low { get; set; }
        public double volume { get; set; }
        public double prev { get; set; }
    }

    public partial class MainPage : PhoneApplicationPage
    {
        // Constructor
        public MainPage()
        {
            InitializeComponent();
        }

        void myStock_GetQuoteCompleted(object sender,
        StockService.GetQuoteCompletedEventArgs e)
        {
            XDocument xReturn = XDocument.Parse(e.Result);

            IEnumerable<StockQuote> myQuote =
            from item in xReturn.Descendants("Stock")
            select new StockQuote
            {
                last = Convert.ToDouble(item.Element("Last").Value),
                date = Convert.ToDateTime(item.Element("Date").Value),
                time = Convert.ToDateTime(item.Element("Time").Value),
                change = Convert.ToDouble(item.Element("Change").Value),
                open = Convert.ToDouble(item.Element("Open").Value),
                high = Convert.ToDouble(item.Element("High").Value),
                low = Convert.ToDouble(item.Element("Low").Value),
                volume = Convert.ToDouble(item.Element("Volume").Value),
                prev = Convert.ToDouble(item.Element("PreviousClose").Value)
            };

            StockQuote thisQuote = myQuote.ElementAt(0);

            if (thisQuote.change > 0)
            {
                txtLast.Foreground = new SolidColorBrush(Colors.Green);
                txtChange.Foreground = new SolidColorBrush(Colors.Green);
            }
            else
            {
                txtLast.Foreground = new SolidColorBrush(Colors.Red);
                txtChange.Foreground = new SolidColorBrush(Colors.Red);
            }

            txtChange.Text = thisQuote.change.ToString("0.00");
            txtHigh.Text = thisQuote.high.ToString();
            txtLow.Text = thisQuote.low.ToString();
            txtLast.Text = thisQuote.last.ToString();
            txtOpen.Text = thisQuote.open.ToString();
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                StockService.StockQuoteSoapClient myStock = new StockService.
                StockQuoteSoapClient();
                myStock.GetQuoteCompleted += new
                EventHandler<StockService.GetQuoteCompletedEventArgs>(myStock_GetQuoteCompleted);

                if (txtTicker.Text == "")
                    txtTicker.Text = "MSFT";
                myStock.GetQuoteAsync(txtTicker.Text);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Invalid Ticker. " + ex.Message);
            }
            
        }
    }
}
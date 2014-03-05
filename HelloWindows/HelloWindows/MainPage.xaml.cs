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

namespace HelloWindows
{
    public partial class MainPage : PhoneApplicationPage
    {
        // Constructor
        public MainPage()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            String nameString = textBox1.Text;
            String msg = "";
            if (nameString.Length > 0)
                msg = "Hello, " + nameString + "!";
            else
                msg = "Hello!";
            textBlock1.Text = msg;

            PageTitle.Text = msg;

            button1.Content = "Thank you!";
        }
    }
}
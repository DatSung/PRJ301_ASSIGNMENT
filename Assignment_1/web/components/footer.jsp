<%-- 
    Document   : footer
    Created on : Oct 24, 2023, 9:01:45 PM
    Author     : uinic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="styles.css">
            <title></title>
            <style>
                /* Footer styles */
                footer {
                    background-color: #333;
                    width: 100%;

                }

                .content {
                    margin: 0 auto;
                    width: 80%;
                    color: #fff;
                    padding: 20px;
                    display: flex;
                    flex-wrap: wrap;
                    justify-content: space-between;
                    align-items: center;
                }
                /* Contact info styles */
                .contact-info {
                    flex: 1;
                    max-width: 100%;
                    margin-bottom: 20px;
                }

                .contact-info h1 {
                    font-size: 24px;
                    margin-bottom: 10px;
                    font-weight: bold;
                }

                .contact-info p {
                    margin: 5px 0;
                }

                .contact-info a {
                    color: #fff;
                    text-decoration: none;
                }

                /* Google map styles */
                .google-map {
                    flex: 1;
                    max-width: 100%;
                }

                .google-map iframe {
                    width: 100%;
                    height: 350px;
                    border: none;
                }

                /* Media query for screens smaller than 768px */
                @media (max-width: 768px) {
                    .contact-info, .google-map {
                        flex: 1;
                        max-width: 100%;
                    }
                }

                /* Media query for screens smaller than 480px */
                @media (max-width: 480px) {
                    .contact-info h1 {
                        font-size: 20px;
                    }
                }

            </style>
        </head>

        <body>
            <footer>
                <div class="content">
                    <div class="contact-info">
                        <h1>Contact Us</h1>
                        <p>FPT Telecom</p>
                        <p>Viet Nam</p>
                        <p>Ho Chi Minh, 700000</p>
                        <p>Phone: (+84) 0988110080</p>
                        <p>Email: <a href="mailto:info@yourwebsite.com">fpt.telecom@gmail.com</a></p>
                    </div>

                    <div class="google-map">
                        <iframe
                            id='map'
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.6099418911194!2d106.80501207655274!3d10.841132830463167!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752731176b07b1%3A0xb752b24b379bae5e!2sFPT%20University%20HCMC!5e0!3m2!1sen!2s!4v1690903773204!5m2!1sen!2s"
                            />
                    </div>
                </div>
            </footer>
        </body>
    </html>

</body>
</html>
